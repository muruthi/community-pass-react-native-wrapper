import React, { useState } from 'react';
import { View, Text, StyleSheet, Dimensions } from 'react-native';
import { getWriteProfile } from 'community-pass-react-native-wrapper';
import BouncyCheckbox from 'react-native-bouncy-checkbox';
import { PROGRAM_GUID, RELIANT_APP_GUID } from '@env';
import type { GetErrorResultType, GetWriteProfileResultType } from 'src/types';

import CustomButton from './components/CustomButton';
import { themeColors } from '../assets/colors';
import {
  buttonLabels,
  screens,
  writeProfileScreenStrings,
  registrationTypes,
} from '../assets/strings';

const { width: WIDTH } = Dimensions.get('screen');

const WriteProfile = ({ route, navigation }: any) => {
  const rId = route?.params?.rId;
  const registrationType = route?.params?.registrationType;
  const [writeProfileError, setWriteProfileError] = useState('');
  const [isLoading, setIsLoading] = useState(false);
  const [overwriteCard, setOverwriteCard] = useState(false);

  const handleWriteProfile = () => {
    setIsLoading(true);
    getWriteProfile({
      reliantAppGUID: RELIANT_APP_GUID,
      programGUID: PROGRAM_GUID,
      rID: rId,
      overwriteCard: overwriteCard,
    })
      .then((res: GetWriteProfileResultType) => {
        console.log(res);
        setIsLoading(false);
        setWriteProfileError('');
        registrationType === registrationTypes.BASIC_USER
          ? navigation.navigate(screens.WRITE_PASSCODE, {
              consumerDeviceNumber: res.consumerDeviceNumber,
              rId: rId,
            })
          : navigation.navigate(screens.WRITE_SUCCESSFUL, {
              consumerDeviceNumber: res.consumerDeviceNumber,
              rId: rId,
            });
      })
      .catch((e: GetErrorResultType) => {
        console.log(JSON.stringify(e, null, 2));
        setWriteProfileError(e?.message);
        setIsLoading(false);
      });
  };

  return (
    <View style={styles.container}>
      <View style={styles.innerContainer}>
        <View style={styles.infoWrapper}>
          <Text style={styles.title}>
            {writeProfileScreenStrings.SCREEN_TITLE}
          </Text>
          <Text style={styles.description}>
            {writeProfileScreenStrings.SCREEN_DESCRIPTION}
          </Text>
          <View style={styles.CheckboxWrapoper}>
            <BouncyCheckbox
              size={25}
              fillColor={themeColors.mastercardYellow}
              unfillColor={themeColors.white}
              text=""
              iconStyle={{
                borderColor: themeColors.mastercardYellow,
              }}
              innerIconStyle={styles.innerIconStyle}
              onPress={(isChecked: boolean) => {
                setOverwriteCard(isChecked);
              }}
            />
            <Text style={styles.description}>
              {writeProfileScreenStrings.OVERWRITE_CARD}
            </Text>
          </View>
        </View>
        <Text style={styles.error}>{writeProfileError}</Text>
        <CustomButton
          isLoading={isLoading}
          onPress={handleWriteProfile}
          label={buttonLabels.WRITE_PROFILE}
          customStyles={styles.button}
          labelStyles={styles.buttonLabel}
          indicatorColor={themeColors.white}
        />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    width: WIDTH,
    padding: 20,
    backgroundColor: themeColors.white,
  },
  button: {
    paddingHorizontal: 15,
    paddingVertical: 10,
    borderRadius: 4,
    width: '100%',
    borderWidth: 2,
  },
  innerIconStyle: {
    borderWidth: 3,
  },
  buttonLabel: {
    color: themeColors.white,
    textAlign: 'center',
  },
  title: {
    fontSize: 20,
    color: themeColors.black,
    marginBottom: 20,
  },
  description: {
    fontSize: 14,
    color: themeColors.black,
  },
  infoWrapper: {
    width: '100%',
  },
  error: {
    color: themeColors.mastercardRed,
    marginVertical: 10,
    textAlign: 'left',
    width: '100%',
  },
  innerContainer: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  CheckboxWrapoper: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 10,
  },
});

export default WriteProfile;

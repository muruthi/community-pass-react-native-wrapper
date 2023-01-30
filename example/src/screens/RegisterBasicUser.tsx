import React, { useState } from 'react';
import { View, Text, StyleSheet, Dimensions } from 'react-native';
import { getRegisterBasicUser } from 'react-native-cpk-library';

import CustomButton from './components/CustomButton';

import { themeColors } from '../assets/colors';
import {
  buttonLabels,
  registerBasicUserScreenStrings,
  registrationTypes,
  screens,
} from '../assets/strings';

const { width: WIDTH } = Dimensions.get('screen');
const RELIANT_APP_GUID: string = '4559ce55-c9a4-40fc-b22a-051244c01ec1';
const PROGRAM_GUID: string = '752a94d5-cf80-45e6-8d2c-305f1b841991';

const RegisterBasicUser = ({ navigation }: any) => {
  const [registrationError, setRegistrationError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const handleRegisterBasicUser = () => {
    setIsLoading(true);
    getRegisterBasicUser({
      reliantAppGUID: RELIANT_APP_GUID,
      programGUID: PROGRAM_GUID,
    })
      .then((res: any) => {
        console.log(res);
        setIsLoading(false);
        navigation.navigate(screens.WRITE_PROFILE, {
          rId: res?.rId,
          registrationType: registrationTypes.BASIC_USER,
        });
      })
      .catch((e: any) => {
        setRegistrationError(e?.message);
        setIsLoading(false);
      });
  };

  return (
    <View style={styles.container}>
      <View style={styles.innerContainer}>
        <View style={styles.infoWrapper}>
          <Text style={styles.title}>
            {registerBasicUserScreenStrings.SCREEN_TITLE}
          </Text>
          <Text style={styles.description}>
            {registerBasicUserScreenStrings.SCREEN_DESCRIPTION}
          </Text>
        </View>
        <Text style={styles.error}>{registrationError}</Text>
        <CustomButton
          isLoading={isLoading}
          onPress={handleRegisterBasicUser}
          label={buttonLabels.REGISTER_USER}
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
});

export default RegisterBasicUser;

import React, { useState } from 'react';
import { View, Text, StyleSheet, Dimensions, Alert } from 'react-native';
import { getRegisterUserWithBiometrics } from 'community-pass-react-native-wrapper';
import { PROGRAM_GUID, RELIANT_APP_GUID } from '@env';

import CustomButton from './components/CustomButton';

import { themeColors } from '../assets/colors';
import {
  buttonLabels,
  registerUserWithBiometricsScreenStrings,
  registrationTypes,
  screens,
} from '../assets/strings';

const { width: WIDTH } = Dimensions.get('screen');

const RegisterUserWithBiometrics = ({ route, navigation }: any) => {
  const consentId = route?.params?.consentId;
  const [registrationError, setRegistrationError] = useState('');
  const [isLoading, setIsLoading] = useState(false);

  const handleCancel = (res: any) => {
    setIsLoading(false);
    navigation.navigate(screens.HOME, {
      rId: res?.rId,
    });
  };

  const handleProceed = (res: any) => {
    setRegistrationError('');
    setIsLoading(false);
    navigation.navigate(screens.WRITE_PROFILE, {
      rId: res?.rId,
      registrationType: registrationTypes.BIOMETRIC_USER,
    });
  };

  const handleRegisterUserWithBiometrics = () => {
    setIsLoading(true);
    getRegisterUserWithBiometrics({
      reliantAppGUID: RELIANT_APP_GUID,
      programGUID: PROGRAM_GUID,
      consentID: consentId,
    })
      .then((res: any) => {
        console.log(res);
        setIsLoading(false);
        res?.enrolmentStatus === 'EXISTING'
          ? Alert.alert(
              registerUserWithBiometricsScreenStrings.ALERT_TITLE,
              registerUserWithBiometricsScreenStrings.ALERT_DESCRIPTION,
              [
                {
                  text: registerUserWithBiometricsScreenStrings.ALERT_CANCEL_BUTTON,
                  onPress: () => handleCancel(res),
                  style: 'cancel',
                },
                {
                  text: registerUserWithBiometricsScreenStrings.ALERT_ACCEPT_BUTTON,
                  onPress: () => handleProceed(res),
                  style: 'default',
                },
              ],
              {
                cancelable: true,
                onDismiss: () => null,
              }
            )
          : handleProceed(res);
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
            {registerUserWithBiometricsScreenStrings.SCREEN_TITLE}
          </Text>
          <Text style={styles.description}>
            {registerUserWithBiometricsScreenStrings.SCREEN_DESCRIPTION}
          </Text>
        </View>
        <Text style={styles.error}>{registrationError}</Text>
        <CustomButton
          isLoading={isLoading}
          onPress={handleRegisterUserWithBiometrics}
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

export default RegisterUserWithBiometrics;
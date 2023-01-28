import React, { useState } from 'react';
import { View, Text, StyleSheet, Dimensions } from 'react-native';
import { saveBiometricConsent } from 'react-native-cpk-library';

import CustomButton from './components/CustomButton';
import { buttonLabels } from '../assets/strings';
import { themeColors } from '../assets/colors';

const { width: WIDTH } = Dimensions.get('screen');
const RELIANT_APP_GUID: string = '4559ce55-c9a4-40fc-b22a-051244c01ec1';
const PROGRAM_GUID: string = '752a94d5-cf80-45e6-8d2c-305f1b841991';

const SaveBiometricConsent = ({ navigation }: any) => {
  const [registrationError, setRegistrationError] = useState(null);
  const [isGrantConsentLoading, setIsGrantConsentLoading] = useState(false);
  const [isDenyConsentLoading, setIsDenyConsentLoading] = useState(false);

  const handleGrantBiometricConsent = () => {
    setIsGrantConsentLoading(true);
    saveBiometricConsent({
      reliantAppGUID: RELIANT_APP_GUID,
      programGUID: PROGRAM_GUID,
      consumerConsentValue: true,
    })
      .then((res: any) => {
        console.log(res);
        setIsGrantConsentLoading(false);
        return res?.consentId;
      })
      .then((id: string) => {
        navigation.navigate('RegisterUserWithBiometrics', {
          consentId: id,
        });
      })
      .catch((e: any) => {
        setRegistrationError(e?.message);
        setIsGrantConsentLoading(false);
      });
  };

  const handleDeclineBiometricConsent = () => {
    setIsDenyConsentLoading(true);
    saveBiometricConsent({
      reliantAppGUID: RELIANT_APP_GUID,
      programGUID: PROGRAM_GUID,
      consumerConsentValue: true,
    })
      .then((res: any) => {
        setIsDenyConsentLoading(false);
        return res?.consentId;
      })
      .then((id: string) => {
        navigation.navigate('RegisterBasicUser', {
          consentId: id,
        });
      })
      .catch((e: any) => {
        setRegistrationError(e?.message);
        setIsDenyConsentLoading(false);
      });
  };

  return (
    <View style={styles.container}>
      {!!registrationError && <Text>{registrationError}</Text>}
      <View style={styles.consentbuttonsWrapper}>
        <CustomButton
          isLoading={isDenyConsentLoading}
          onPress={handleDeclineBiometricConsent}
          label={buttonLabels.DENY_CONSENT}
          customStyles={styles.declineConsentButton}
          labelStyles={styles.declineConsentButtonLabel}
        />
        <CustomButton
          isLoading={isGrantConsentLoading}
          onPress={handleGrantBiometricConsent}
          label={buttonLabels.GRANT_CONSENT}
          customStyles={styles.grantConsentButton}
          labelStyles={styles.grantConsentButtonLabel}
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
  },
  consentbuttonsWrapper: {
    width: '100%',
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  grantConsentButton: {
    minWidth: '48%',
  },
  declineConsentButton: {
    backgroundColor: themeColors.white,
    minWidth: '48%',
    borderWidth: 2,
    borderColor: themeColors.mastercardYellow,
  },
  grantConsentButtonLabel: {
    color: themeColors.white,
    textAlign: 'center',
  },
  declineConsentButtonLabel: {
    color: themeColors.mastercardYellow,
    textAlign: 'center',
  },
});

export default SaveBiometricConsent;

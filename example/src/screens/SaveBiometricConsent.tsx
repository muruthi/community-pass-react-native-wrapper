import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  ActivityIndicator,
  TouchableOpacity,
  Dimensions,
} from 'react-native';
import { saveBiometricConsent } from 'react-native-cpk-library';

const { width: WIDTH } = Dimensions.get('screen');
const RELIANT_APP_GUID: string = '4559ce55-c9a4-40fc-b22a-051244c01ec1';
const PROGRAM_GUID: string = '752a94d5-cf80-45e6-8d2c-305f1b841991';

const SaveBiometricConsent = ({ navigation }: any) => {
  const [registrationError, setRegistrationError] = useState(null);
  const [isGrantConsentLoading, setIsGrantConsentLoading] = useState(false);
  const [isDenyConsentLoading, setIsDenyConsentLoading] = useState(false);

  const handleGrantBiometricConsent = () => {
    setIsGrantConsentLoading(true);
    saveBiometricConsent(RELIANT_APP_GUID, PROGRAM_GUID, true)
      .then((res: any) => {
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
    saveBiometricConsent(RELIANT_APP_GUID, PROGRAM_GUID, false)
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
        <TouchableOpacity
          onPress={handleDeclineBiometricConsent}
          style={[styles.declineConsentButton, styles.button]}
          disabled={isDenyConsentLoading || isGrantConsentLoading}
        >
          {isDenyConsentLoading ? (
            <ActivityIndicator size="small" color="#000000" />
          ) : (
            <Text style={styles.declineConsentButtonLabel}>
              Decline Consent
            </Text>
          )}
        </TouchableOpacity>
        <TouchableOpacity
          onPress={handleGrantBiometricConsent}
          style={[styles.grantConsentButton, styles.button]}
          disabled={isGrantConsentLoading || isDenyConsentLoading}
        >
          {isGrantConsentLoading ? (
            <ActivityIndicator size="small" color="#ffffff" />
          ) : (
            <Text style={styles.grantConsentButtonLabel}>Grant Consent</Text>
          )}
        </TouchableOpacity>
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
    backgroundColor: '#000000',
  },
  declineConsentButton: {
    backgroundColor: '#ffffff',
  },
  button: {
    paddingHorizontal: 15,
    paddingVertical: 10,
    borderRadius: 4,
    minWidth: '48%',
    borderWidth: 2,
    borderColor: '#000000',
  },
  grantConsentButtonLabel: {
    color: '#ffffff',
    textAlign: 'center',
  },
  declineConsentButtonLabel: {
    color: '#000000',
    textAlign: 'center',
  },
});

export default SaveBiometricConsent;

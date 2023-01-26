import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  ActivityIndicator,
  TouchableOpacity,
  Dimensions,
} from 'react-native';
import { getRegisterUserWithBiometrics } from 'react-native-cpk-library';

const { width: WIDTH } = Dimensions.get('screen');
const RELIANT_APP_GUID: string = '4559ce55-c9a4-40fc-b22a-051244c01ec1';
const PROGRAM_GUID: string = '752a94d5-cf80-45e6-8d2c-305f1b841991';

const RegisterUserWithBiometrics = ({ route, navigation }: any) => {
  //   const [rId, setRid] = useState(null);
  const consentId = route?.params?.consentId;
  const [registrationError, setRegistrationError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const handleRegisterUserWithBiometrics = () => {
    setIsLoading(true);
    getRegisterUserWithBiometrics(RELIANT_APP_GUID, PROGRAM_GUID, consentId)
      .then((res: any) => {
        console.log(res);
        navigation.navigate('WriteProfile');
        setIsLoading(false);
      })
      .catch((e: any) => {
        setRegistrationError(e?.message);
        setIsLoading(false);
      });
  };

  return (
    <View style={styles.container}>
      {!!registrationError && <Text>{registrationError}</Text>}
      {!!consentId && <Text>consent ID: {consentId}</Text>}
      <View style={styles.consentbuttonsWrapper}>
        <TouchableOpacity
          onPress={handleRegisterUserWithBiometrics}
          style={[styles.declineConsentButton, styles.button]}
          disabled={isLoading}
        >
          {isLoading ? (
            <ActivityIndicator size="small" color="#000000" />
          ) : (
            <Text style={styles.declineConsentButtonLabel}>
              Register User With Biometrics
            </Text>
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
    width: '100%',
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

export default RegisterUserWithBiometrics;

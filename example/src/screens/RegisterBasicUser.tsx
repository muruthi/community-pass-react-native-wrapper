import React, { useState } from 'react';
import {
  View,
  Text,
  StyleSheet,
  ActivityIndicator,
  TouchableOpacity,
  Dimensions,
} from 'react-native';
import { getRegisterBasicUser } from 'react-native-cpk-library';

const { width: WIDTH } = Dimensions.get('screen');
const RELIANT_APP_GUID: string = '4559ce55-c9a4-40fc-b22a-051244c01ec1';
const PROGRAM_GUID: string = '752a94d5-cf80-45e6-8d2c-305f1b841991';

const RegisterBasicUser = ({ route, navigation }: any) => {
  const consentId = route?.params?.consentId;
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
        navigation.navigate('WriteProfile', { rId: res?.rId });
      })
      .catch((e: any) => {
        setRegistrationError(e?.message);
        setIsLoading(false);
      });
  };

  return (
    <View style={styles.container}>
      {!!registrationError && <Text>{registrationError}</Text>}
      {!!consentId && <Text>{consentId}</Text>}
      <View style={styles.consentbuttonsWrapper}>
        <TouchableOpacity
          onPress={handleRegisterBasicUser}
          style={[styles.declineConsentButton, styles.button]}
          disabled={isLoading}
        >
          {isLoading ? (
            <ActivityIndicator size="small" color="#000000" />
          ) : (
            <Text style={styles.declineConsentButtonLabel}>
              Register User Without Biometrics
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

export default RegisterBasicUser;

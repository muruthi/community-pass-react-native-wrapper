import React, { useState } from 'react';
import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
  Dimensions,
  ActivityIndicator,
} from 'react-native';
import { getWritePasscode } from 'react-native-cpk-library';

const { width: WIDTH } = Dimensions.get('screen');
const RELIANT_APP_GUID: string = '4559ce55-c9a4-40fc-b22a-051244c01ec1';
const PROGRAM_GUID: string = '752a94d5-cf80-45e6-8d2c-305f1b841991';
const RID: string = '';
const PASSCODE: string = '123456';

const WritePasscode = () => {
  const [writePasscodeError, setWritePasscodeError] = useState(null);
  const [isLoading, setIsLoading] = useState(false);

  const handleWritePasscode = () => {
    setIsLoading(true);
    getWritePasscode({
      reliantAppGUID: RELIANT_APP_GUID,
      programGUID: PROGRAM_GUID,
      rID: RID,
      passcode: PASSCODE,
    })
      .then((res: any) => {
        console.log(res);
        setIsLoading(false);
      })
      .catch((e: any) => {
        setWritePasscodeError(e?.message);
        setIsLoading(false);
      });
  };

  return (
    <View style={styles.container}>
      {!!writePasscodeError && <Text>{writePasscodeError}</Text>}
      {/* {consentId && <Text>{consentId}</Text>} */}
      <View style={styles.consentbuttonsWrapper}>
        <TouchableOpacity
          onPress={handleWritePasscode}
          style={[styles.declineConsentButton, styles.button]}
          disabled={isLoading}
        >
          {isLoading ? (
            <ActivityIndicator size="small" color="#000000" />
          ) : (
            <Text style={styles.declineConsentButtonLabel}>
              Write Passcode to Card
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

export default WritePasscode;

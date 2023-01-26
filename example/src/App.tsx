import * as React from 'react';

import { StyleSheet, View, Text, Button } from 'react-native';
import { getRegisterUserWithBiometrics } from 'react-native-cpk-library';

export default function App() {
  const RELIANT_APP_GUID: string = '4559ce55-c9a4-40fc-b22a-051244c01ec1';
  const PROGRAM_GUID: string = '752a94d5-cf80-45e6-8d2c-305f1b841991';
  const CONSENT_ID: string = '752a94d5-cf80-45e6-8d2c-305f1b841991';
  // const OVERWRITE: boolean = true;

  // React.useEffect(() => {
  //   connectKernelService(RELIANT_APP_GUID).then((res: any) => console.log(res));
  //   //checkRegistrationStatus(P)
  //   //checkRegistrationStatus(PROGRAM_GUID, RELIANT_APP_GUID).then((res: any) => console.log(res))
  //   //startBioRegistration(PROGRAM_GUID, RELIANT_APP_GUID).then((res: any) => console.log(res))
  //   //authenticateWithPasscode(PROGRAM_GUID, RELIANT_APP_GUID, "999999").then((res: any) => console.log(res))
  // }, []);

  const startRegistrationWithBioToken = () => {
    // resgisterWithBio(PROGRAM_GUID, RELIANT_APP_GUID, OVERWRITE).then(
    //   (res: any) => console.log(res)
    getRegisterUserWithBiometrics(
      RELIANT_APP_GUID,
      PROGRAM_GUID,
      CONSENT_ID
    ).then((res: any) => {
      console.log(res);
    });
  };
  return (
    <View style={styles.container}>
      <Text>CPK Test</Text>
      <Button
        title="Start Bio Registration"
        onPress={startRegistrationWithBioToken}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});

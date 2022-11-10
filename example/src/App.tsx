import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { authenticateWithPasscode } from 'react-native-cpk-library';

export default function App() {

  const RELIANT_APP_GUID = "4559ce55-c9a4-40fc-b22a-051244c01ec1"
  const PROGRAM_GUID = "752a94d5-cf80-45e6-8d2c-305f1b841991"

  React.useEffect(() => {
    //checkRegistrationStatus(P)
    //checkRegistrationStatus(PROGRAM_GUID, RELIANT_APP_GUID).then((res: any) => console.log(res))
    //startBioRegistration(PROGRAM_GUID, RELIANT_APP_GUID).then((res: any) => console.log(res))
    authenticateWithPasscode(PROGRAM_GUID, RELIANT_APP_GUID, "999999").then((res: any) => console.log(res))
  }, []);

  return (
    <View style={styles.container}>
      <Text>CPK Test</Text>
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

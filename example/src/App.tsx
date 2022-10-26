import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { connect } from 'react-native-cpk-library';

export default function App() {
  const RELIANT_APP_GUID = "4559ce55-c9a4-40fc-b22a-051244c01ec1"

  React.useEffect(() => {
    connect(RELIANT_APP_GUID).then(res => console.log(res))
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

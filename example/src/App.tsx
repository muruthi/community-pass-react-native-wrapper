import * as React from 'react';

import { StyleSheet, View, Text } from 'react-native';
import { connect, hello } from 'react-native-cpk-library';

export default function App() {
  const [result, setResult] = React.useState<number | undefined>();
  const RELIANT_APP_GUID = "4559ce55-c9a4-40fc-b22a-051244c01ec1"

  React.useEffect(() => {
    //multiply(3, 7).then(setResult);
    //console.log(RELIANT_APP_GUID)
    connect(RELIANT_APP_GUID).then(res => console.log(res))
  }, []);

  return (
    <View style={styles.container}>
      <Text>Result: {result}</Text>
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

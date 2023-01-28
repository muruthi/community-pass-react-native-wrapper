import 'react-native-gesture-handler';
import React from 'react';
import {
  TransitionPresets,
  createStackNavigator,
} from '@react-navigation/stack';
import { NavigationContainer } from '@react-navigation/native';

import SaveBiometricConsent from './screens/SaveBiometricConsent';
import WritePasscode from './screens/WritePasscode';
import WriteProfile from './screens/WriteProfile';
import RegisterUserWithBiometrics from './screens/RegisterUserWithBiometrics';
import RegisterBasicUser from './screens/RegisterBasicUser';
import Home from './screens/Home';
import PreTransactions from './screens/PreTransactions';
import UserDataForm from './screens/UserDataForm';

const Stack = createStackNavigator();

export default function App() {
  return (
    <NavigationContainer>
      <Stack.Navigator
        initialRouteName="Home"
        screenOptions={{
          headerMode: 'screen',
          headerTintColor: 'white',
          headerStyle: { backgroundColor: '#F79E1B' },
          ...TransitionPresets.SlideFromRightIOS,
        }}
      >
        <Stack.Screen
          name="Home"
          options={{
            headerTitle: 'Community Pass React Native App',
          }}
          component={Home}
        />
        <Stack.Screen
          name="SaveBiometricConsent"
          options={{
            headerTitle: 'Save Biometric Consent',
          }}
          component={SaveBiometricConsent}
        />
        <Stack.Screen
          name="RegisterUserWithBiometrics"
          options={{
            headerTitle: 'Register User With Biometrics',
          }}
          component={RegisterUserWithBiometrics}
        />
        <Stack.Screen
          name="RegisterBasicUser"
          options={{
            headerTitle: 'Register basic User',
          }}
          component={RegisterBasicUser}
        />
        <Stack.Screen
          name="WritePasscode"
          options={{
            headerTitle: 'Write Passcode',
          }}
          component={WritePasscode}
        />
        <Stack.Screen
          name="WriteProfile"
          options={{
            headerTitle: 'Write Profile',
          }}
          component={WriteProfile}
        />
        <Stack.Screen
          name="PreTransactions"
          options={{
            headerTitle: 'Pre Transactions',
          }}
          component={PreTransactions}
        />
        <Stack.Screen
          name="UserDataForm"
          options={{
            headerTitle: 'Enter User Data',
          }}
          component={UserDataForm}
        />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

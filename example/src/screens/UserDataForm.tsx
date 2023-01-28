import React, { useState } from 'react';
import { StyleSheet, View } from 'react-native';

import CustomInput from './components/CustomInput';
import CustomButton from './components/CustomButton';
import { buttonLabels } from '../assets/strings';

const UserDataForm = () => {
  const [isLoading, setIsLoading] = useState(false);
  const [firstName, setFirstName] = useState('');
  const [lastName, setLastName] = useState('');
  const [address, setAddress] = useState('');
  const [emailAddress, setEmailAddress] = useState('');
  const [phoneNumber, setPhoneNumber] = useState('');
  const [age, setAge] = useState('');

  const handleButtonPress = () => {
    setIsLoading(false);
  };

  return (
    <View style={styles.container}>
      <CustomInput
        config={{
          placeholderText: 'First Name',
          keyboadType: 'default',
          hasError: false,
        }}
        value={firstName}
        onChange={setFirstName}
      />
      <CustomInput
        config={{
          placeholderText: 'Last Name',
          keyboadType: 'default',
          hasError: false,
        }}
        value={lastName}
        onChange={setLastName}
      />
      <CustomInput
        config={{
          placeholderText: 'Address',
          keyboadType: 'default',
          hasError: false,
        }}
        value={address}
        onChange={setAddress}
      />
      <CustomInput
        config={{
          placeholderText: 'Email Address',
          keyboadType: 'email-address',
          hasError: false,
        }}
        value={emailAddress}
        onChange={setEmailAddress}
      />
      <CustomInput
        config={{
          placeholderText: 'Phone Number',
          keyboadType: 'phone-pad',
          hasError: false,
        }}
        value={phoneNumber}
        onChange={setPhoneNumber}
      />
      <CustomInput
        config={{
          placeholderText: 'Age',
          keyboadType: 'numeric',
          hasError: false,
        }}
        value={age}
        onChange={setAge}
      />

      <CustomButton
        isLoading={isLoading}
        onPress={handleButtonPress}
        label={buttonLabels.ADD_USER_DATA}
        customStyles=""
        labelStyles=""
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
  },

  button: {
    borderRadius: 4,
    width: '100%',
    borderWidth: 2,
    borderColor: '#000000',
    backgroundColor: '#fff',
    height: 50,
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
  },
  buttonLabel: {
    color: '#000',
    textAlign: 'center',
  },
});

export default UserDataForm;

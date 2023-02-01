import React, { useState } from 'react';
import { StyleSheet, View } from 'react-native';

import CustomInput from './components/CustomInput';
import CustomButton from './components/CustomButton';
import {
  buttonLabels,
  keyboardTypes,
  userDataScreenStrings,
} from '../assets/strings';
import { themeColors } from '../assets/colors';

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
          placeholderText: userDataScreenStrings.FIRST_NAME_PLACEHOLDER,
          keyboadType: keyboardTypes.DEFAULT,
          hasError: false,
        }}
        value={firstName}
        onChange={setFirstName}
      />
      <CustomInput
        config={{
          placeholderText: userDataScreenStrings.LAST_NAME_PLACEHOLDER,
          keyboadType: keyboardTypes.DEFAULT,
          hasError: false,
        }}
        value={lastName}
        onChange={setLastName}
      />
      <CustomInput
        config={{
          placeholderText: userDataScreenStrings.ADDRESS_PLACEHOLDER,
          keyboadType: keyboardTypes.DEFAULT,
          hasError: false,
        }}
        value={address}
        onChange={setAddress}
      />
      <CustomInput
        config={{
          placeholderText: userDataScreenStrings.EMAIL_ADDRESS_PLACEHOLDER,
          keyboadType: keyboardTypes.EMAIL_ADDRESS,
          hasError: false,
        }}
        value={emailAddress}
        onChange={setEmailAddress}
      />
      <CustomInput
        config={{
          placeholderText: userDataScreenStrings.PHONE_NUMBER_PLACEHOLDER,
          keyboadType: keyboardTypes.PHONE_PAD,
          hasError: false,
        }}
        value={phoneNumber}
        onChange={setPhoneNumber}
      />
      <CustomInput
        config={{
          placeholderText: userDataScreenStrings.AGE_PLACEHOLDERL,
          keyboadType: keyboardTypes.NUMERIC,
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
        indicatorColor={themeColors.white}
      />
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: themeColors.white,
  },
  button: {
    borderRadius: 4,
    width: '100%',
    borderWidth: 2,
    borderColor: themeColors.black,
    backgroundColor: themeColors.white,
    height: 50,
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    marginBottom: 15,
  },
  buttonLabel: {
    color: themeColors.black,
    textAlign: 'center',
  },
});

export default UserDataForm;

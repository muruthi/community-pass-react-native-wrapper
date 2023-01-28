import React from 'react';
import { View, TextInput, StyleSheet } from 'react-native';

const CustomInput = ({ config, value, onChange }: any) => {
  return (
    <>
      <View style={[styles.container, config.hasError && styles.errorInput]}>
        <TextInput
          style={styles.textInput}
          autoCorrect={false}
          autoCapitalize="none"
          value={value}
          selectionColor={'#000000'}
          placeholder={config.placeholderText}
          keyboardType={config.keyboadType}
          onChangeText={onChange}
        />
      </View>
    </>
  );
};

const styles = StyleSheet.create({
  container: {
    borderColor: '#eaeaea',
    borderWidth: StyleSheet.hairlineWidth,
    borderRadius: 5,
    backgroundColor: '#eaeaea',
    marginBottom: 15,
  },
  textInput: {
    paddingHorizontal: 10,
  },
  errorText: {
    fontSize: 9,
    color: 'red',
    width: '100%',
    position: 'absolute',
    marginTop: 10,
  },
  errorInput: {
    borderColor: 'red',
  },
});

export default CustomInput;

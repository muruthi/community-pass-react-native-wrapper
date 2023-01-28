import {
  StyleSheet,
  Text,
  TouchableOpacity,
  ActivityIndicator,
} from 'react-native';
import React from 'react';

import { themeColors } from '../../assets/colors';

interface PropTypes {
  isLoading: boolean;
  onPress: () => void;
  label: string;
  customStyles: any;
  labelStyles: any;
}

const CustomButton = ({
  isLoading,
  onPress,
  label,
  customStyles = '',
  labelStyles = '',
}: PropTypes) => {
  return (
    <TouchableOpacity
      onPress={onPress}
      style={[styles.button, customStyles]}
      disabled={isLoading}
    >
      {isLoading ? (
        <ActivityIndicator size="small" color={themeColors.white} />
      ) : (
        <Text style={[styles.buttonLabel, labelStyles]}>{label}</Text>
      )}
    </TouchableOpacity>
  );
};

const styles = StyleSheet.create({
  button: {
    borderRadius: 4,
    borderWidth: 2,
    borderColor: themeColors.mastercardYellow,
    backgroundColor: themeColors.mastercardYellow,
    height: 50,
    display: 'flex',
    flexDirection: 'column',
    alignItems: 'center',
    justifyContent: 'center',
    color: themeColors.white,
    textAlign: 'center',
    fontSize: 16,
    fontWeight: '500',
  },
  buttonLabel: {
    color: themeColors.white,
    textAlign: 'center',
    fontSize: 16,
    fontWeight: '500',
  },
});

export default CustomButton;

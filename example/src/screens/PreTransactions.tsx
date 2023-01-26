import React from 'react';
import {
  View,
  Text,
  StyleSheet,
  TouchableOpacity,
  ToastAndroid,
} from 'react-native';

const PreTransactions = ({ navigation }: any) => {
  const showToast = (message: string) => {
    ToastAndroid.show(
      `${message} has not yet been implemented.`,
      ToastAndroid.SHORT
    );
  };

  return (
    <View style={styles.container}>
      <TouchableOpacity
        style={styles.card}
        onPress={() => navigation.navigate('SaveBiometricConsent')}
      >
        <Text style={styles.sectionLabel}>Action</Text>
        <Text style={styles.cardTitle}>Enroll a New User</Text>
        <Text style={styles.cardDescription}>
          enroll a user using either biometrics or passcode
        </Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.card}
        onPress={() => showToast('Shared Space')}
      >
        <Text style={styles.sectionLabel}>Action</Text>
        <Text style={styles.cardTitle}>Use the Shared Space</Text>
        <Text style={styles.cardDescription}>
          Sync data between the POI and the Card
        </Text>
      </TouchableOpacity>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
  },
  card: {
    backgroundColor: '#ffffff',
    borderRadius: 5,
    padding: 30,
    marginBottom: 30,
  },
  cardTitle: {
    color: '#000000',
    marginBottom: 10,
    fontWeight: '600',
    fontSize: 16,
  },
  sectionLabel: {
    color: '#000000',
    marginBottom: 10,
    fontWeight: '400',
    fontSize: 14,
  },
  cardDescription: {
    color: '#999999',
    marginBottom: 10,
    fontSize: 14,
  },
});

export default PreTransactions;

import React from 'react';
import {
  View,
  Text,
  TouchableOpacity,
  StyleSheet,
  ToastAndroid,
} from 'react-native';

const Home = ({ navigation }: any) => {
  const showToast = (message: string) => {
    ToastAndroid.show(
      `${message} have not yet been implemented.`,
      ToastAndroid.SHORT
    );
  };

  return (
    <View style={styles.container}>
      <TouchableOpacity
        style={styles.card}
        onPress={() => navigation.navigate('PreTransactions')}
      >
        <Text style={styles.sectionLabel}>Section</Text>
        <Text style={styles.cardTitle}>Pre-Transactions</Text>
        <Text style={styles.cardDescription}>
          You will complete user setup and on-boarding in this phase
        </Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.card}
        onPress={() => showToast('Transactions')}
      >
        <Text style={styles.sectionLabel}>Section</Text>
        <Text style={styles.cardTitle}>Transactions</Text>
        <Text style={styles.cardDescription}>
          You will conduct a life transaction during this phase
        </Text>
      </TouchableOpacity>
      <TouchableOpacity
        style={styles.card}
        onPress={() => showToast('Admin Transactions')}
      >
        <Text style={styles.sectionLabel}>Section</Text>
        <Text style={styles.cardTitle}>Admin-Transactions</Text>
        <Text style={styles.cardDescription}>
          You can make changes to a user profile in this phase
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

export default Home;

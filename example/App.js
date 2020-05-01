/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { useState } from 'react';
import { SafeAreaView, StyleSheet, Text } from 'react-native';

import MonthPicker from 'react-native-month-picker';

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center' },
});

const App = () => {
  const [date, setDate] = useState(null);

  return (
    <SafeAreaView style={styles.container}>
      <Text>Month Picker Example</Text>
      <Text>{date}</Text>
      <MonthPicker onValueChange={setDate} />
    </SafeAreaView>
  );
};

export default App;

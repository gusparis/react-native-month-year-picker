/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { useState } from 'react';
import { SafeAreaView, StyleSheet, Text } from 'react-native';

import MonthPicker from 'react-native-month-year-picker';

const styles = StyleSheet.create({
  container: { flex: 1, justifyContent: 'center', alignItems: 'center' },
});

const App = () => {
  const [date, setDate] = useState(new Date());

  const onValueChange = (event, newDate) => setDate(newDate);

  return (
    <SafeAreaView style={styles.container}>
      <Text>Month Picker Example</Text>
      <MonthPicker
        onChange={onValueChange}
        value={date}
        minimumDate={new Date()}
        maximumDate={new Date(2025, 5)}
      />
    </SafeAreaView>
  );
};

export default App;

/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { useState, useCallback } from 'react';
import { SafeAreaView, StyleSheet, Text, TouchableOpacity } from 'react-native';
import moment from 'moment';

import MonthPicker, { Locale } from 'react-native-month-year-picker';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#dfe6e9',
  },
  button: {
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#b2bec3',
    borderRadius: 4,
    padding: 10,
  },
  buttonText: {
    color: '#2d3436',
  },
});

const DEFAULT_FORMAT = 'MM-YYYY';
const DEFAULT_OUTPUT_FORMAT = 'MMM-YYYY';

const App = () => {
  const [date, setDate] = useState(new Date());
  const [show, setShow] = useState(false);

  const showPicker = useCallback((value) => setShow(value), []);

  const onValueChange = useCallback(
    (event, newDate) => {
      const selectedDate = newDate || date;

      showPicker(false);
      setDate(selectedDate);
    },
    [date, showPicker],
  );

  return (
    <SafeAreaView style={styles.container}>
      <Text>Month Year Picker Example</Text>
      <Text>{moment(date, DEFAULT_FORMAT).format(DEFAULT_OUTPUT_FORMAT)}</Text>
      <TouchableOpacity onPress={() => showPicker(true)} style={styles.button}>
        <Text style={styles.buttonText}>OPEN</Text>
      </TouchableOpacity>
      {show && (
        <MonthPicker
          onChange={onValueChange}
          value={date}
          minimumDate={new Date()}
          maximumDate={new Date(2025, 5)}
          enableAutoDarkMode={false}
          useLocale={Locale.RUSSIAN}
        />
      )}
    </SafeAreaView>
  );
};

export default App;

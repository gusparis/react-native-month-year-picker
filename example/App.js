/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React, { useState } from 'react';
import {
  SafeAreaView,
  StyleSheet,
  Text,
  TouchableOpacity,
  Platform,
} from 'react-native';
import moment from 'moment';

import MonthPicker from 'react-native-month-year-picker';

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'lightyellow',
  },
  button: {
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: 'lightgrey',
    borderRadius: 4,
    padding: 10,
  },
});

const DEFAULT_FORMAT = 'MM-YYYY';
const DEFAULT_OUTPUT_FORMAT = 'MMM-YYYY';

const App = () => {
  const [date, setDate] = useState(new Date());
  const [show, setShow] = useState(false);

  const showPicker = (value) => setShow(value);

  const onValueChange = (event, newDate) => {
    const selectedDate = newDate || date;

    showPicker(Platform.OS === 'ios');
    setDate(selectedDate);
  };

  return (
    <SafeAreaView style={styles.container}>
      <Text>Month Picker Example</Text>
      <Text>{moment(date, DEFAULT_FORMAT).format(DEFAULT_OUTPUT_FORMAT)}</Text>
      <TouchableOpacity onPress={() => showPicker(true)} style={styles.button}>
        <Text>OPEN</Text>
      </TouchableOpacity>
      {show ? (
        <MonthPicker
          onChange={onValueChange}
          value={date}
          minimumDate={new Date()}
          maximumDate={new Date(2025, 5)}
        />
      ) : null}
    </SafeAreaView>
  );
};

export default App;

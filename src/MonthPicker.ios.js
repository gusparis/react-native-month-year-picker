import React from 'react';
import { View, StyleSheet } from 'react-native';
import moment from 'moment';

import RNMonthPickerView from './RNMonthPickerNativeComponent';

const NATIVE_FORMAT = 'M-YYYY';
const DEFAULT_OUTPUT_FORMAT = 'MM-YYYY';

const styles = StyleSheet.create({
  container: { height: 200, minWidth: 315 },
  picker: { flex: 1 },
});

const MonthPicker = ({ onValueChange, outputFormat, ...restProps }) => {
  const onChange = (event) => {
    const date = moment(event.nativeEvent.newDate, NATIVE_FORMAT).format(
      outputFormat || DEFAULT_OUTPUT_FORMAT,
    );
    onValueChange(date);
  };

  return (
    <View style={styles.container}>
      <RNMonthPickerView
        style={styles.picker}
        onChange={onChange}
        {...restProps}
      />
    </View>
  );
};

export default MonthPicker;

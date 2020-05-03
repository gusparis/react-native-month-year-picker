import React from 'react';
import { View, StyleSheet } from 'react-native';
import moment from 'moment';
import invariant from 'invariant';

import RNMonthPickerView from './RNMonthPickerNativeComponent';

const NATIVE_FORMAT = 'M-YYYY';
const DEFAULT_OUTPUT_FORMAT = 'MM-YYYY';

const styles = StyleSheet.create({
  container: { height: 200, minWidth: 315 },
  picker: { flex: 1 },
});

const MonthPicker = ({ onChange, value, outputFormat, ...restProps }) => {
  invariant(value, 'value prop is required!');

  const getLongFromDate = (selectedValue) =>
    moment(selectedValue, outputFormat || DEFAULT_OUTPUT_FORMAT).valueOf();

  const onValueChange = (event) => {
    const date = moment(event.nativeEvent.newDate, NATIVE_FORMAT).format(
      outputFormat || DEFAULT_OUTPUT_FORMAT,
    );
    onChange(event, date);
  };

  return (
    <View style={styles.container}>
      <RNMonthPickerView
        style={styles.picker}
        onChange={onValueChange}
        value={getLongFromDate(value)}
        {...restProps}
      />
    </View>
  );
};

export default MonthPicker;

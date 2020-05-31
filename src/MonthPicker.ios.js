import React, { useEffect } from 'react';
import {
  View,
  StyleSheet,
  Dimensions,
  Animated,
  Text,
  TouchableOpacity,
} from 'react-native';
import moment from 'moment';
import invariant from 'invariant';

import RNMonthPickerView from './RNMonthPickerNativeComponent';

const { width } = Dimensions.get('screen');
const NATIVE_FORMAT = 'M-YYYY';
const DEFAULT_OUTPUT_FORMAT = 'MM-YYYY';
const { Value, timing } = Animated;

const styles = StyleSheet.create({
  container: {
    width,
    backgroundColor: 'white',
    position: 'absolute',
    zIndex: 500,
    bottom: 0,
  },
  toolbarContainer: {
    justifyContent: 'space-between',
    flexDirection: 'row',
    paddingHorizontal: 30,
    paddingVertical: 10,
  },
  pickerContainer: { height: 200, minWidth: 315 },
  picker: { flex: 1 },
});

const MonthPicker = ({
  onChange,
  value,
  minimumDate,
  maximumDate,
  outputFormat,
  okButton = 'OK',
  cancelButton = 'Cancel',
}) => {
  invariant(value, 'value prop is required!');
  const slideAnim = new Value(0);

  const slideIn = () => {
    timing(slideAnim, {
      toValue: 250,
      duration: 400,
      useNativeDriver: false,
    }).start();
  };

  slideIn();

  const getLongFromDate = (selectedValue) =>
    moment(selectedValue, outputFormat || DEFAULT_OUTPUT_FORMAT).valueOf();

  const onValueChange = (event) => {
    const date = moment(event.nativeEvent.newDate, NATIVE_FORMAT).format(
      outputFormat || DEFAULT_OUTPUT_FORMAT,
    );
    onChange(event, date);
  };

  return (
    <Animated.View style={{ ...styles.container, height: slideAnim }}>
      <View style={styles.toolbarContainer}>
        <TouchableOpacity>
          <Text>{cancelButton}</Text>
        </TouchableOpacity>
        <TouchableOpacity>
          <Text>{okButton}</Text>
        </TouchableOpacity>
      </View>
      <View style={styles.pickerContainer}>
        <RNMonthPickerView
          style={styles.picker}
          onChange={onValueChange}
          value={getLongFromDate(value)}
          minimumDate={getLongFromDate(minimumDate)}
          maximumDate={getLongFromDate(maximumDate)}
        />
      </View>
    </Animated.View>
  );
};

export default MonthPicker;

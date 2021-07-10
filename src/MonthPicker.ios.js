import React, { useState, useCallback, useEffect } from 'react';
import { View, StyleSheet, Dimensions, Animated } from 'react-native';
import moment from 'moment';
import invariant from 'invariant';

import RNMonthPickerView from './RNMonthPickerNativeComponent';
import {
  ACTION_DATE_SET,
  ACTION_DISMISSED,
  ACTION_NEUTRAL,
  NATIVE_FORMAT,
  DEFAULT_MODE,
} from './constants';

const { width } = Dimensions.get('screen');
const { Value, timing } = Animated;

const styles = StyleSheet.create({
  container: {
    width,
    position: 'absolute',
    zIndex: 500,
    bottom: 0,
  },
  pickerContainer: {
    height: 244,
    width,
  },
  picker: { flex: 1 },
});

const MonthPicker = ({
  value,
  minimumDate,
  maximumDate,
  onChange: onAction,
  locale = '',
  mode = DEFAULT_MODE,
  okButton,
  cancelButton,
  neutralButton,
  autoTheme = true,
}) => {
  invariant(value, 'value prop is required!');

  const [opacity] = useState(new Value(0));
  const [selectedDate, setSelectedDate] = useState(value);

  useEffect(() => {
    timing(opacity, {
      toValue: 1,
      duration: 300,
      useNativeDriver: true,
    }).start();
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const onChange = useCallback(
    ({ nativeEvent: { newDate } }) =>
      setSelectedDate(moment(newDate, NATIVE_FORMAT).toDate()),
    [],
  );

  const slideOut = useCallback(
    callback =>
      timing(opacity, {
        toValue: 0,
        duration: 250,
        useNativeDriver: true,
      }).start(callback),
    // eslint-disable-next-line react-hooks/exhaustive-deps
    [],
  );

  const onDone = useCallback(() => {
    slideOut(
      ({ finished }) =>
        finished && onAction && onAction(ACTION_DATE_SET, selectedDate),
    );
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [selectedDate]);

  const onCancel = useCallback(() => {
    slideOut(
      ({ finished }) =>
        finished && onAction && onAction(ACTION_DISMISSED, undefined),
    );
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, []);

  const onNeutral = useCallback(() => {
    slideOut(
      ({ finished }) =>
        finished && onAction && onAction(ACTION_NEUTRAL, selectedDate),
    );
    // eslint-disable-next-line react-hooks/exhaustive-deps
  }, [selectedDate]);

  return (
    <Animated.View
      style={{
        ...styles.container,
        opacity,
        transform: [
          {
            translateY: opacity.interpolate({
              inputRange: [0.4, 1],
              outputRange: [150, 0],
            }),
          },
        ],
      }}>
      <View style={styles.pickerContainer}>
        <RNMonthPickerView
          {...{
            locale,
            mode,
            onChange,
            onDone,
            onCancel,
            onNeutral,
            okButton,
            cancelButton,
            neutralButton,
            autoTheme,
          }}
          style={styles.picker}
          value={value.getTime()}
          minimumDate={minimumDate?.getTime() ?? null}
          maximumDate={maximumDate?.getTime() ?? null}
        />
      </View>
    </Animated.View>
  );
};

export default MonthPicker;

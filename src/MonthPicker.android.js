import React from 'react';
import { View, StyleSheet } from 'react-native';
import moment from 'moment';
import invariant from 'invariant';

import RNMonthPickerDialogModule from './RNMonthPickerDialogModule';

const NATIVE_FORMAT = 'YYYY-MM';
const DEFAULT_OUTPUT_FORMAT = 'MM-YYYY';

export const ACTION_DATE_SET = 'dateSetAction';
export const ACTION_DISMISSED = 'dismissedAction';

const MonthPicker = ({
  value,
  onChange,
  outputFormat,
  minimumDate,
  maximumDate,
  okButton = 'OK',
  cancelButton = 'Cancel',
}) => {
  invariant(value, 'value prop is required!');
  let picker;

  picker = RNMonthPickerDialogModule.open({
    value: moment(value, outputFormat || DEFAULT_OUTPUT_FORMAT).valueOf(),
    minimumDate: moment(minimumDate).valueOf(),
    maximumDate: moment(maximumDate).valueOf(),
    okButton,
    cancelButton,
  });

  picker.then(
    function resolve({ action, year, month }) {
      let date;
      switch (action) {
        case ACTION_DATE_SET:
          date = moment(`${year}-${month}`, NATIVE_FORMAT).format(
            outputFormat || DEFAULT_OUTPUT_FORMAT,
          );
          break;
        case ACTION_DISMISSED:
        default:
          date = undefined;
      }
      onChange(action, date);
    },
    function reject(error) {
      throw error;
    },
  );

  return null;
};

export default MonthPicker;

import moment from 'moment';
import invariant from 'invariant';

import RNMonthPickerDialogModule from './RNMonthPickerDialogModule';
import { ACTION_DATE_SET, ACTION_DISMISSED, NATIVE_FORMAT } from './constants';

const DEFAULT_OUTPUT_FORMAT = 'MM-YYYY';

const MonthPicker = ({
  value,
  onChange,
  outputFormat,
  minimumDate,
  maximumDate,
  ...restProps
}) => {
  invariant(value, 'value prop is required!');

  RNMonthPickerDialogModule.open({
    value: value.getTime(),
    minimumDate: minimumDate.getTime(),
    maximumDate: maximumDate.getTime(),
    ...restProps,
  }).then(
    ({ action, year, month }) => {
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
      onChange && onChange(action, date);
    },
    (error) => {
      throw error;
    },
  );

  return null;
};

export default MonthPicker;

import React from 'react';
import { requireNativeComponent } from 'react-native';

const RNMonthPickerView = props => <RNMonthPicker {...props} />

var RNMonthPicker = requireNativeComponent('RNMonthPicker', RNMonthPickerView);

export default RNMonthPicker;
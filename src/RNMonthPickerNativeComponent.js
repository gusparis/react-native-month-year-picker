import React from 'react';
import { requireNativeComponent } from 'react-native';

class RNMonthPickerView extends React.Component {
  render() {
    return <RNMonthPicker {...this.props} />;
  }
}

const RNMonthPicker = requireNativeComponent('RNMonthPicker', RNMonthPickerView);

export default RNMonthPickerView;

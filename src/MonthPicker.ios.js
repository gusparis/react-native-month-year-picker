import React from 'react';
import { View, StyleSheet } from 'react-native';
import RNMonthPickerView from './RNMonthPickerNativeComponent';

const styles = StyleSheet.create({
  container: { height: 200, minWidth: 315 },
});

class MonthPicker extends React.Component {
  onChange = (value) => this.props.onValueChange(value);

  render() {
    return (
      <View style={styles.container}>
        <RNMonthPickerView onChange={this.onChange} {...this.props} />
      </View>
    );
  }
}

export default MonthPicker;

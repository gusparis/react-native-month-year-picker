import React, { useCallback } from 'react';
import { View, StyleSheet } from 'react-native';
import moment from 'moment';

import RNMonthPickerView from './RNMonthPickerNativeComponent';

const styles = StyleSheet.create({
  container: { height: 200, minWidth: 315 },
  picker: {flex: 1}
});

const MonthPicker = ({ onValueChange, restProps }) => {
  const onChange = useCallback(
    (event) => {
      console.log("newDate", event.nativeEvent.newDate);
      // const date = moment(event.newDate).format("MM-YYYY");
      // onValueChange(date)
    }
    , [onValueChange]
  );

  return (
    <View style={styles.container}>
      <RNMonthPickerView style={styles.picker} onChange={onChange} {...restProps}/>
    </View>
  );
}

export default MonthPicker;

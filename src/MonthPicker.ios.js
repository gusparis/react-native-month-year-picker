import React from 'react';
import { View, StyleSheet, Dimensions, Animated } from 'react-native';
import moment from 'moment';
import invariant from 'invariant';

import RNMonthPickerView from './RNMonthPickerNativeComponent';
import { ACTION_DATE_SET, ACTION_DISMISSED, NATIVE_FORMAT } from './constants';

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

class MonthPicker extends React.PureComponent {
  state = {
    opacity: new Value(0),
    currentDate: this.props.value,
  };

  slideIn = () => {
    timing(this.state.opacity, {
      toValue: 1,
      duration: 300,
      useNativeDriver: true,
    }).start();
  };

  slideOut = (callBack) => {
    timing(this.state.opacity, {
      toValue: 0,
      duration: 250,
      useNativeDriver: true,
    }).start(callBack);
  };

  onValueChange = (event) => {
    const {
      nativeEvent: { newDate },
    } = event;
    const date = moment(newDate, NATIVE_FORMAT).toDate();
    this.setState({ currentDate: date });
  };

  onConfirm = () => {
    this.slideOut(
      ({ finished }) =>
        finished &&
        this.props.onChange &&
        this.props.onChange(ACTION_DATE_SET, this.state.currentDate),
    );
  };

  onCancel = () => {
    this.slideOut(
      ({ finished }) =>
        finished &&
        this.props.onChange &&
        this.props.onChange(ACTION_DISMISSED, undefined),
    );
  };

  componentDidMount() {
    this.slideIn();
  }

  render() {
    const {
      value,
      minimumDate,
      maximumDate,
      backgroundColor = null,
      locale = null,
    } = this.props;
    invariant(value, 'value prop is required!');

    return (
      <Animated.View
        style={{
          ...styles.container,
          opacity: this.state.opacity,
          transform: [
            {
              translateY: this.state.opacity.interpolate({
                inputRange: [0, 1],
                outputRange: [150, 0],
              }),
            },
          ],
        }}>
        <View style={styles.pickerContainer}>
          <RNMonthPickerView
            {...{ backgroundColor, locale }}
            value={value.getTime()}
            minimumDate={minimumDate.getTime()}
            maximumDate={maximumDate.getTime()}
            style={styles.picker}
            onChange={this.onValueChange}
            onCancel={this.onCancel}
            onDone={this.onConfirm}
          />
        </View>
      </Animated.View>
    );
  }
}

export default MonthPicker;

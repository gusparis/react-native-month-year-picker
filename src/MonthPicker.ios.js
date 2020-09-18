import React from 'react';
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
import { ACTION_DATE_SET, ACTION_DISMISSED } from './utils';

const { width } = Dimensions.get('screen');
const NATIVE_FORMAT = 'M-YYYY';
const DEFAULT_OUTPUT_FORMAT = 'MM-YYYY';
const { Value, timing } = Animated;

const styles = StyleSheet.create({
  container: {
    width,
    position: 'absolute',
    zIndex: 500,
    bottom: 0,
  },
  toolbarContainer: {
    justifyContent: 'space-between',
    alignItems: 'center',
    flexDirection: 'row',
    paddingHorizontal: 30,
    paddingVertical: 10,
  },
  pickerContainer: {
    height: 215,
    width,
  },
  picker: { flex: 1 },
  okStyle: {
    fontWeight: '500',
    letterSpacing: 1.0,
  },
  cancelStyle: {
    fontWeight: '400',
    letterSpacing: 0.25,
  },
});

class MonthPicker extends React.PureComponent {
  state = {
    opacity: new Value(0),
    currentDate: this.props.value,
  };

  slideIn = () => {
    timing(this.state.opacity, {
      toValue: 1,
      duration: 400,
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

  getLongFromDate = (selectedValue) =>
    moment(
      selectedValue,
      this.props.outputFormat || DEFAULT_OUTPUT_FORMAT,
    ).valueOf();

  onValueChange = (event) => {
    const date = moment(event.nativeEvent.newDate, NATIVE_FORMAT).format(
      this.props.outputFormat || DEFAULT_OUTPUT_FORMAT,
    );
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
      okButton,
      cancelButton,
      okButtonStyle,
      cancelButtonStyle,
      textColor = null,
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
        {/* <View style={styles.toolbarContainer}>
          <TouchableOpacity onPress={this.onCancel}>
            <Text>{cancelButton || 'Cancel'}</Text>
          </TouchableOpacity>
          <TouchableOpacity onPress={this.onConfirm}>
            <Text>{okButton || 'OK'}</Text>
          </TouchableOpacity>
        </View> */}
        <View style={styles.pickerContainer}>
          <RNMonthPickerView
            {...{ textColor, backgroundColor, locale }}
            style={styles.picker}
            onChange={this.onValueChange}
            value={this.getLongFromDate(value)}
            minimumDate={this.getLongFromDate(minimumDate)}
            maximumDate={this.getLongFromDate(maximumDate)}
          />
        </View>
      </Animated.View>
    );
  }
}

export default MonthPicker;

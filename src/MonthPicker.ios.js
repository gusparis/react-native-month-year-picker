import React from 'react';
import {
  View,
  StyleSheet,
  Dimensions,
  Animated,
  Text,
  TouchableOpacity,
  NativeModules,
} from 'react-native';
import moment from 'moment';
import invariant from 'invariant';

import RNMonthPickerView from './RNMonthPickerNativeComponent';
import { ACTION_DATE_SET, ACTION_DISMISSED, useTheme } from './utils';

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
  pickerContainer: { height: 200, minWidth: 315 },
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
    slideAnim: new Value(0),
    currentDate: this.props.value,
    theme: {},
  };

  slideIn = () => {
    timing(this.state.slideAnim, {
      toValue: 250,
      duration: 400,
      useNativeDriver: false,
    }).start();
  };

  slideOut = (callBack) => {
    timing(this.state.slideAnim, {
      toValue: 0,
      duration: 250,
      useNativeDriver: false,
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
        this.props.onChange(ACTION_DATE_SET, this.state.currentDate),
    );
  };

  onCancel = () => {
    this.slideOut(
      ({ finished }) =>
        finished && this.props.onChange(ACTION_DISMISSED, undefined),
    );
  };

  componentDidMount() {
    const darkMode = NativeModules.RNDarkModeManager;
    darkMode.isDarkMode((error, isDark) => {
      const theme = this.props.enableAutoDarkMode ? isDark : !isDark;
      this.setState({ theme: useTheme(theme) }, () => this.slideIn());
    });
  }

  render() {
    const {
      value,
      minimumDate,
      maximumDate,
      okButton = 'OK',
      cancelButton = 'Cancel',
      okButtonStyle,
      cancelButtonStyle,
      enableAutoDarkMode = true,
    } = this.props;
    const { theme } = this.state;
    invariant(value, 'value prop is required!');

    return (
      <Animated.View
        style={{
          ...styles.container,
          ...theme.container,
          height: this.state.slideAnim,
        }}>
        <View style={styles.toolbarContainer}>
          <TouchableOpacity onPress={this.onCancel}>
            <Text
              style={
                cancelButtonStyle || [styles.cancelStyle, theme.cancelButton]
              }>
              {cancelButton}
            </Text>
          </TouchableOpacity>
          <TouchableOpacity onPress={this.onConfirm}>
            <Text style={okButtonStyle || [styles.okStyle, theme.okButton]}>
              {okButton}
            </Text>
          </TouchableOpacity>
        </View>
        <View style={styles.pickerContainer}>
          <RNMonthPickerView
            style={styles.picker}
            onChange={this.onValueChange}
            value={this.getLongFromDate(value)}
            minimumDate={this.getLongFromDate(minimumDate)}
            maximumDate={this.getLongFromDate(maximumDate)}
            enableAutoDarkMode={enableAutoDarkMode}
          />
        </View>
      </Animated.View>
    );
  }
}

export default MonthPicker;

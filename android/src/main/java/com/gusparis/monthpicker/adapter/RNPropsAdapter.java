package com.gusparis.monthpicker.adapter;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;

import java.util.Locale;

import javax.annotation.Nullable;

import static com.gusparis.monthpicker.adapter.RNProps.AUTO_THEME;
import static com.gusparis.monthpicker.adapter.RNProps.CANCEL_BUTTON;
import static com.gusparis.monthpicker.adapter.RNProps.LOCALE;
import static com.gusparis.monthpicker.adapter.RNProps.MAXIMUM_VALUE;
import static com.gusparis.monthpicker.adapter.RNProps.MINIMUM_VALUE;
import static com.gusparis.monthpicker.adapter.RNProps.MODE;
import static com.gusparis.monthpicker.adapter.RNProps.NEUTRAL_BUTTON;
import static com.gusparis.monthpicker.adapter.RNProps.OK_BUTTON;
import static com.gusparis.monthpicker.adapter.RNProps.VALUE;

public class RNPropsAdapter implements RNMonthPickerProps {

  private ReadableMap props;
  private PickerDialogListener listener;

  public RNPropsAdapter(@Nullable ReadableMap props,
                        Promise promise,
                        ReactContext reactContext) {
    this.props = props;
    this.listener = new PickerDialogListener(promise, reactContext);
  }

  @Override
  public RNDate value() {
    return new RNDate(props, VALUE);
  }

  @Override
  public RNDate minimumValue() {
    if (props.isNull(MINIMUM_VALUE.value())) {
      return null;
    }
    return new RNDate(props, MINIMUM_VALUE);
  }

  @Override
  public RNDate maximumValue() {
    if (props.isNull(MAXIMUM_VALUE.value())) {
      return null;
    }
    return new RNDate(props, MAXIMUM_VALUE);
  }

  @Override
  public String okButton() {
    return getStringValue(OK_BUTTON, "Done");
  }

  @Override
  public String cancelButton() {
    return getStringValue(CANCEL_BUTTON, "Cancel");
  }

  @Override
  public String neutralButton() {
    return getStringValue(NEUTRAL_BUTTON, null);
  }

  @Override
  public Locale locale() {
    String locale = getStringValue(LOCALE, null);
    if (locale == null) {
      return Locale.getDefault();
    }
    return new Locale(locale);
  }

  @Override
  public String mode() {
    return getStringValue(MODE, "full");
  }

  @Override
  public Boolean autoTheme() {
    return !props.hasKey(AUTO_THEME.value()) || props.getBoolean(AUTO_THEME.value());
  }

  @Override
  public void onChange(int year, int month, int flag) {
    listener.onDateSet(null, year, month, flag);
  }

  @Override
  public void onChange() {
    listener.onDismiss(null);
  }

  private String getStringValue(RNProps prop, String defaultValue) {
    return props.hasKey(prop.value()) ? props.getString(prop.value()) : defaultValue;
  }
}

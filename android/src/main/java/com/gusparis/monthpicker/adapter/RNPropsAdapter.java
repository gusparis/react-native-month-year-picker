package com.gusparis.monthpicker.adapter;

import android.os.Build;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReadableMap;

import java.time.Instant;
import java.time.LocalDate;
import java.util.TimeZone;

import javax.annotation.Nullable;

import androidx.annotation.RequiresApi;

import static com.gusparis.monthpicker.adapter.RNProps.CANCEL_BUTTON;
import static com.gusparis.monthpicker.adapter.RNProps.ENABLE_AUTO_DARK_MODE;
import static com.gusparis.monthpicker.adapter.RNProps.MAXIMUM_VALUE;
import static com.gusparis.monthpicker.adapter.RNProps.MINIMUM_VALUE;
import static com.gusparis.monthpicker.adapter.RNProps.OK_BUTTON;
import static com.gusparis.monthpicker.adapter.RNProps.VALUE;

@RequiresApi(api = Build.VERSION_CODES.O)
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
  public LocalDate value() {
    return getLocalDateValue(VALUE);
  }

  @Override
  public LocalDate minimumValue() {
    return getLocalDateValue(MINIMUM_VALUE);
  }

  @Override
  public LocalDate maximumValue() {
    return getLocalDateValue(MAXIMUM_VALUE);
  }

  @Override
  public String okButton() {
    return getStringValue(OK_BUTTON);
  }

  @Override
  public String cancelButton() {
    return getStringValue(CANCEL_BUTTON);
  }

  @Override
  public Boolean enableAutoDarkMode() {
    return getBooleanValue(ENABLE_AUTO_DARK_MODE);
  }

  @Override
  public void onChange(int year, int month) {
    listener.onDateSet(null, year, month, 1);
  }

  @Override
  public void onChange() {
    listener.onDismiss(null);
  }

  private LocalDate getLocalDateValue(RNProps prop) {
    return props.hasKey(prop.value()) ?
        Instant.ofEpochMilli((long) props.getDouble(prop.value()))
            .atZone(TimeZone.getDefault().toZoneId())
            .toLocalDate() :
        null;
  }

  private String getStringValue(RNProps prop) {
    return props.hasKey(prop.value()) ? props.getString(prop.value()) : null;
  }

  private Boolean getBooleanValue(RNProps prop) {
    return props.hasKey(prop.value()) ? props.getBoolean(prop.value()) : null;
  }
}

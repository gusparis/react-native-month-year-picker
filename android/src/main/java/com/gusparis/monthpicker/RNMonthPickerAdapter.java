package com.gusparis.monthpicker;

import android.os.Build;

import com.facebook.react.bridge.ReadableMap;
import com.gusparis.monthpicker.utils.IRNConstants;

import java.time.Instant;
import java.time.LocalDate;
import java.util.TimeZone;

import javax.annotation.Nullable;

import androidx.annotation.RequiresApi;

public class RNMonthPickerAdapter {

  private LocalDate value;
  private LocalDate maximumValue;
  private LocalDate minimumValue;
  private String cancelButton;
  private String okButton;

  @RequiresApi(api = Build.VERSION_CODES.O)
  public RNMonthPickerAdapter(@Nullable ReadableMap options) {
    assert options != null;
    value = setLocalDate(options, IRNConstants.VALUE);
    maximumValue = setLocalDate(options, IRNConstants.MAXIMUM_VALUE);
    minimumValue = setLocalDate(options, IRNConstants.MINIMUM_VALUE);
    okButton = getString(options, IRNConstants.OK_BUTTON);
    cancelButton = getString(options, IRNConstants.CANCEL_BUTTON);
  }

  private String getString(ReadableMap options, String key) {
    return options.hasKey(key) ? options.getString(key) : null;
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private LocalDate setLocalDate(ReadableMap options, String key) {
    return options.hasKey(key) ?
        Instant.ofEpochMilli((long) options.getDouble(key))
            .atZone(TimeZone.getDefault().toZoneId())
            .toLocalDate() :
        null;
  }

  public LocalDate getValue() {
    return value;
  }

  public LocalDate getMaximumValue() {
    return maximumValue;
  }

  public LocalDate getMinimumValue() {
    return minimumValue;
  }

  public String getCancelButton() {
    return cancelButton;
  }

  public String getOkButton() {
    return okButton;
  }
}

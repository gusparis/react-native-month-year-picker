package com.gusparis.monthpicker.adapter;

import com.facebook.react.bridge.ReadableMap;

import java.util.Calendar;

public class RNDate {

  private Calendar now;

  RNDate(ReadableMap props, RNProps prop) {
    now = Calendar.getInstance();
    if (props.hasKey(prop.value())) {
      now.setTimeInMillis((long) props.getDouble(prop.value()));
    }
  }

  public int getYear() {
    return now.get(Calendar.YEAR);
  }

  public int getMonth() {
    return now.get(Calendar.MONTH);
  }
}

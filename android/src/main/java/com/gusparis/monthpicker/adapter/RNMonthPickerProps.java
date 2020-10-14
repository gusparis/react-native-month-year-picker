package com.gusparis.monthpicker.adapter;

import java.util.Locale;

public interface RNMonthPickerProps {

  RNDate value();

  RNDate minimumValue();

  RNDate maximumValue();

  String okButton();

  String cancelButton();

  Locale locale();

  void onChange(int year, int month);

  void onChange();
}

package com.gusparis.monthpicker.adapter;

import java.util.Locale;

public interface RNMonthPickerProps {

  DateProp value();

  DateProp minimumValue();

  DateProp maximumValue();

  String okButton();

  String cancelButton();

  Boolean enableAutoDarkMode();

  Locale useLocale();

  void onChange(int year, int month);

  void onChange();
}

package com.gusparis.monthpicker.adapter;

public interface RNMonthPickerProps {

  DateProp value();

  DateProp minimumValue();

  DateProp maximumValue();

  String okButton();

  String cancelButton();

  Boolean enableAutoDarkMode();

  void onChange(int year, int month);

  void onChange();
}

package com.gusparis.monthpicker.adapter;

import java.time.LocalDate;

public interface RNMonthPickerProps {

  LocalDate value();

  LocalDate minimumValue();

  LocalDate maximumValue();

  String okButton();

  String cancelButton();

  Boolean enableAutoDarkMode();

  void onChange(int year, int month);

  void onChange();
}

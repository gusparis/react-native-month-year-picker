package com.gusparis.monthpicker.builder;

import java.text.DateFormatSymbols;

class MonthNumberPicker extends MonthYearNumberPicker {

  @Override
  MonthNumberPicker onScrollListener(MonthYearScrollListener scrollListener) {
    monthPicker.setOnScrollListener(scrollListener);
    return this;
  }

  @Override
  MonthNumberPicker build() {
    String [] months = new DateFormatSymbols(props.useLocale())
        .getMonths();
    monthPicker.setMinValue(0);
    monthPicker.setMaxValue(11);
    monthPicker.setDisplayedValues(months);
    monthPicker.setWrapSelectorWheel(false);
    monthPicker.setValue(props.value().getMonth());
    return this;
  }

  @Override
  synchronized void setValue() {
    int month = monthPicker.getValue();
    int year = yearPicker.getValue();
    int value = month;
    if (props.minimumValue() != null &&
        year == props.minimumValue().getYear() &&
        month < props.minimumValue().getMonth()) {
      value = props.minimumValue().getMonth();
    } else if (props.maximumValue() != null &&
        year == props.maximumValue().getYear() &&
        month > props.maximumValue().getMonth()) {
      value = props.maximumValue().getMonth();
    }
    monthPicker.setValue(value);
  }

  @Override
  int getValue() {
    return monthPicker.getValue();
  }
}

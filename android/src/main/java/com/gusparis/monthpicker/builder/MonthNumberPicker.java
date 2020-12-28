package com.gusparis.monthpicker.builder;

import java.text.DateFormatSymbols;

class MonthNumberPicker extends MonthYearNumberPicker {

  @Override
  MonthNumberPicker onScrollListener(MonthYearScrollListener scrollListener) {
    picker(monthPicker).setOnScrollListener(scrollListener);
    picker(monthPicker).setOnValueChangedListener(scrollListener);
    return this;
  }

  @Override
  MonthNumberPicker build() {
    DateFormatSymbols dfs = new DateFormatSymbols(props.locale());

    picker(monthPicker).setMinValue(0);
    picker(monthPicker).setMaxValue(11);
    picker(monthPicker).setFormatter(MonthFormatter.getMonthFormatter(props.mode(), dfs));
    picker(monthPicker).setWrapSelectorWheel(false);
    picker(monthPicker).setValue(props.value().getMonth());
    // Fix for Formatter blank initial rendering
    monthPicker.incrementByOne(true);
    return this;
  }

  @Override
  synchronized void setValue() {
    if (monthPicker.getCounter() > 0) {
      return;
    }
    int month = picker(monthPicker).getValue();
    int year = picker(yearPicker).getValue();
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
    monthPicker.run(value - month);
  }

  @Override
  int getValue() {
    return picker(monthPicker).getValue();
  }
}

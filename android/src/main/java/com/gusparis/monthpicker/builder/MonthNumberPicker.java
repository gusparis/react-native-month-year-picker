package com.gusparis.monthpicker.builder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormatSymbols;

class MonthNumberPicker extends MonthYearNumberPicker {

  @Override
  MonthNumberPicker onScrollListener(MonthYearScrollListener scrollListener) {
    monthPicker.setOnScrollListener(scrollListener);
    monthPicker.setOnValueChangedListener(scrollListener);
    return this;
  }

  @Override
  MonthNumberPicker build() {
    DateFormatSymbols dfs = new DateFormatSymbols(props.locale());

    monthPicker.setMinValue(0);
    monthPicker.setMaxValue(11);
    monthPicker.setFormatter(MonthFormatter.getMonthFormatter(props.mode(), dfs));
    monthPicker.setWrapSelectorWheel(false);
    monthPicker.setValue(props.value().getMonth());
    // Fix for Formatter blank initial rendering
    monthPicker.incrementByOne(true);
    return this;
  }

  @Override
  synchronized void setValue() {
    if (monthPicker.getCounter() > 0) {
      return;
    }
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
    monthPicker.run(value - month);
  }

  @Override
  int getValue() {
    return monthPicker.getValue();
  }
}

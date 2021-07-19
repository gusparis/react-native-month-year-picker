package com.gusparis.monthpicker.builder;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormatSymbols;

class MonthNumberPicker extends MonthYearNumberPicker {
  private static final Integer DEFAULT_MONTH_SIZE = 3000;

  @Override
  MonthNumberPicker onScrollListener(MonthYearScrollListener scrollListener) {
    monthPicker.setOnScrollListener(scrollListener);
    monthPicker.setOnValueChangedListener(scrollListener);
    return this;
  }

  @Override
  MonthNumberPicker build() {
    final DateFormatSymbols dfs = new DateFormatSymbols(props.locale());

    monthPicker.setMinValue(0);
    monthPicker.setMaxValue(DEFAULT_MONTH_SIZE);
    monthPicker.setFormatter(MonthFormatter.getMonthFormatter(props.mode(), dfs));
    monthPicker.setWrapSelectorWheel(false);
    monthPicker.setValue((DEFAULT_MONTH_SIZE / 2) + props.value().getMonth());
    // Fix for Formatter blank initial rendering
    try {
      final Method method = monthPicker.getClass().getDeclaredMethod("changeValueByOne", boolean.class);
      method.setAccessible(true);
      method.invoke(monthPicker, true);

    } catch (final NoSuchMethodException | InvocationTargetException |
        IllegalAccessException | IllegalArgumentException e) {
      e.printStackTrace();
    }
    return this;
  }

  @Override
  synchronized void setValue() {
    final int row = monthPicker.getValue();
    final int month = (row % 12);
    final int year = yearPicker.getValue();
    int value = row;
    if (props.minimumValue() != null &&
        year == props.minimumValue().getYear() &&
        month < props.minimumValue().getMonth()) {
      value = row + props.minimumValue().getMonth() - month;
    } else if (props.maximumValue() != null &&
        year == props.maximumValue().getYear() &&
        month > props.maximumValue().getMonth()) {
      value = row + props.maximumValue().getMonth() - month;
    }
    monthPicker.setValue(value);
  }

  @Override
  int getValue() {
    return monthPicker.getValue() % 12;
  }
}

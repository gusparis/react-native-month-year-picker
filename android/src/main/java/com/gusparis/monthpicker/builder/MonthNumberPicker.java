package com.gusparis.monthpicker.builder;

import android.os.Build;

import java.text.DateFormatSymbols;
import java.util.Objects;

import androidx.annotation.RequiresApi;

@RequiresApi(api = Build.VERSION_CODES.O)
class MonthNumberPicker extends MonthYearNumberPicker {

  private final String[] MONTHS = new DateFormatSymbols().getShortMonths();

  @Override
  MonthNumberPicker onScrollListener(MonthYearScrollListener scrollListener) {
    monthPicker.setOnScrollListener(scrollListener);
    return this;
  }

  @Override
  MonthNumberPicker build() {
    monthPicker.setMinValue(0);
    monthPicker.setMaxValue(11);
    monthPicker.setDisplayedValues(MONTHS);
    monthPicker.setWrapSelectorWheel(false);
    monthPicker.setValue(props.value().getMonthValue() - 1);
    return this;
  }

  @Override
  synchronized void setValue() {
    int month = monthPicker.getValue();
    int year = yearPicker.getValue();
    int value = month;
    if (Objects.nonNull(props.minimumValue()) &&
        year == props.minimumValue().getYear() &&
        month < props.minimumValue().getMonthValue() - 1) {
      value = props.minimumValue().getMonthValue() - 1;
    } else if (Objects.nonNull(props.maximumValue()) &&
        year == props.maximumValue().getYear() &&
        month > props.maximumValue().getMonthValue() - 1) {
      value = props.maximumValue().getMonthValue() - 1;
    }
    monthPicker.setValue(value);
  }

  @Override
  int getValue() {
    return monthPicker.getValue();
  }
}

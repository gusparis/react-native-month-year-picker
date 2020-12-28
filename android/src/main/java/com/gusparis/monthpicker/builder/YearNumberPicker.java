package com.gusparis.monthpicker.builder;

class YearNumberPicker extends MonthYearNumberPicker {

  private static final int DEFAULT_SIZE = 204;

  @Override
  YearNumberPicker onScrollListener(MonthYearScrollListener scrollListener) {
    picker(yearPicker).setOnScrollListener(scrollListener);
    picker(yearPicker).setOnValueChangedListener(scrollListener);
    return this;
  }

  @Override
  YearNumberPicker build() {
    int year = props.value().getYear();
    picker(yearPicker).setMinValue(year - DEFAULT_SIZE);
    picker(yearPicker).setMaxValue(year + DEFAULT_SIZE);
    picker(yearPicker).setValue(year);
    return this;
  }

  @Override
  synchronized void setValue() {
    if (yearPicker.getCounter() > 0) {
      return;
    }
    int year = picker(yearPicker).getValue();
    int value = year;
    if (props.minimumValue() != null && year < props.minimumValue().getYear()) {
      value = props.minimumValue().getYear();
    } else if (props.maximumValue() != null && year > props.maximumValue().getYear()) {
      value = props.maximumValue().getYear();
    }
    yearPicker.run(value - year);
  }

  @Override
  int getValue() {
    return picker(yearPicker).getValue();
  }
}

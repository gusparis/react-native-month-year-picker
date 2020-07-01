package com.gusparis.monthpicker.builder;

import android.view.View;
import android.widget.NumberPicker;

import com.gusparis.monthpicker.R;
import com.gusparis.monthpicker.adapter.RNMonthPickerProps;

import java.util.Observable;
import java.util.Observer;

abstract class MonthYearNumberPicker implements Observer {

  private static final int MONTH_PICKER_ID = R.id.month_picker;
  private static final int YEAR_PICKER_ID = R.id.year_picker;

  NumberPicker monthPicker;
  NumberPicker yearPicker;
  RNMonthPickerProps props;

  MonthYearNumberPicker view(View view) {
    this.monthPicker = view.findViewById(MONTH_PICKER_ID);
    this.yearPicker = view.findViewById(YEAR_PICKER_ID);
    return this;
  }

  MonthYearNumberPicker props(RNMonthPickerProps props) {
    this.props = props;
    return this;
  }

  @Override
  public void update(Observable o, Object arg) {
    setValue();
  }

  abstract void setValue();

  abstract int getValue();

  abstract MonthYearNumberPicker onScrollListener(MonthYearScrollListener scrollListener);

  abstract MonthYearNumberPicker build();
}

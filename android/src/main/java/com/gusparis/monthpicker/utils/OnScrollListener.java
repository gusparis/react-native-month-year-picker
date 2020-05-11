package com.gusparis.monthpicker.utils;

import android.os.Build;
import android.widget.NumberPicker;
import android.widget.Scroller;

import com.gusparis.monthpicker.R;

import java.time.LocalDate;
import java.util.Objects;

import androidx.annotation.RequiresApi;

public class OnScrollListener implements NumberPicker.OnScrollListener {

  private NumberPicker monthPicker;
  private NumberPicker yearPicker;
  private LocalDate minimumDate;
  private LocalDate maximumDate;

  public OnScrollListener(NumberPicker monthPicker, NumberPicker yearPicker, LocalDate minimumDate, LocalDate maximumDate) {
    this.monthPicker = monthPicker;
    this.yearPicker = yearPicker;
    this.minimumDate = minimumDate;
    this.maximumDate = maximumDate;
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private int getSelectedMonthRow() {
    int month = monthPicker.getValue();
    int year = yearPicker.getValue();
    if (Objects.nonNull(minimumDate) && year == minimumDate.getYear() && month < minimumDate.getMonthValue() - 1) {
      return minimumDate.getMonthValue() - 1;
    } else if (Objects.nonNull(maximumDate) && year == maximumDate.getYear() && month > maximumDate.getMonthValue() - 1) {
      return maximumDate.getMonthValue() - 1;
    } else {
      return month;
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private int getSelectedYearRow() {
    int year = yearPicker.getValue();
    if (Objects.nonNull(minimumDate) && year < minimumDate.getYear()) {
      return minimumDate.getYear();
    } else if (Objects.nonNull(maximumDate) && year > maximumDate.getYear()) {
      return maximumDate.getYear();
    } else {
      return year;
    }
  }

  @Override
  @RequiresApi(api = Build.VERSION_CODES.O)
  public void onScrollStateChange(NumberPicker view, int scrollState) {
    if (scrollState == SCROLL_STATE_IDLE) {
      if (view.getId() == R.id.month_picker) {
        view.setValue(getSelectedMonthRow());
      } else {
        view.setValue(getSelectedYearRow());
        monthPicker.setValue(getSelectedMonthRow());
      }
    }
  }
}

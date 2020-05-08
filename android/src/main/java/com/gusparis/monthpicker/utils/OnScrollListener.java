package com.gusparis.monthpicker.utils;

import android.os.Build;
import android.widget.NumberPicker;

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
      return minimumDate.getMonthValue() - month - 1;
    } else if(Objects.nonNull(maximumDate) && year == maximumDate.getYear() && month > maximumDate.getMonthValue() - 1) {
      return maximumDate.getMonthValue() - month - 1;
    } else {
      return 0;
    }
  }

  @RequiresApi(api = Build.VERSION_CODES.O)
  private int getSelectedYearRow() {
    int year = yearPicker.getValue();
    if (Objects.nonNull(minimumDate) && year < minimumDate.getYear()) {
      return (minimumDate.getYear() - yearPicker.getMinValue()) - (year - yearPicker.getMinValue());
    } else if(Objects.nonNull(maximumDate) && year > maximumDate.getYear()) {
      return (maximumDate.getYear() - yearPicker.getMinValue()) - (year - yearPicker.getMinValue());
    } else {
      return 0;
    }
  }

  @Override
  @RequiresApi(api = Build.VERSION_CODES.O)
  public void onScrollStateChange(NumberPicker view, int scrollState) {
    int movement;
    if (view.getId() == R.id.month_picker) {
      movement = getSelectedMonthRow();
      if (movement != 0) {
        new IncreaseValue(view, movement).run(0);
      }
    } else {
      movement = getSelectedYearRow();
      if (movement != 0) {
        new IncreaseValue(view, movement).run(0);
      }
      movement = getSelectedMonthRow();
      if (movement != 0) {
        new IncreaseValue(monthPicker, movement).run(0);
      }
    }
  }
}

package com.gusparis.monthpicker.adapter;

import java.util.Date;

public class DateProp {

  private static final int FIXED_YEAR = 1900;

  private Date date;

  DateProp(Long epoch) {
    this.date = new Date(epoch);
  }

  public int getYear() {
    return date.getYear() + FIXED_YEAR;
  }

  public int getMonth() {
    return date.getMonth();
  }
}

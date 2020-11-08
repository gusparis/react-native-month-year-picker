package com.gusparis.monthpicker.builder;

import android.annotation.SuppressLint;
import android.widget.NumberPicker;

import java.text.DateFormatSymbols;

public class MonthFormatter {

  public static NumberPicker.Formatter getMonthFormatter(String type, DateFormatSymbols dfs) {
    switch (type) {
      case "short":
        return new ShortMonth(dfs);
      case "number":
        return new NumberMonth();
      case "shortNumber":
        return new ShortNumberMonth();
      default:
        return new FullMonth(dfs);
    }
  }

  private static class FullMonth implements NumberPicker.Formatter {
    private String [] months;

    public FullMonth(DateFormatSymbols dfs) {
      months = dfs.getMonths();
    }

    @Override
    public String format(int i) {
      return months[i];
    }
  }

  private static class ShortMonth implements NumberPicker.Formatter {
    private String [] months;

    public ShortMonth(DateFormatSymbols dfs) {
      months = dfs.getShortMonths();
    }

    @Override
    public String format(int i) {
      return months[i];
    }
  }

  private static class NumberMonth implements NumberPicker.Formatter {

    @Override
    public String format(int i) {
      return String.format("%02d", i + 1);
    }
  }

  private static class ShortNumberMonth implements NumberPicker.Formatter {

    @Override
    public String format(int i) {
      return String.valueOf(i + 1);
    }
  }
}

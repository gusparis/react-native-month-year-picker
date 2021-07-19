package com.gusparis.monthpicker.builder;

import android.widget.NumberPicker;

import java.text.DateFormatSymbols;
import java.util.Arrays;

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
//      String [] newMonths = new String[3000];
//      String [] monthNames = dfs.getMonths();
//      for (int i = 0; i < 3000; i ++) {
//        newMonths[i] = monthNames[i % 12];
//      }
      months = dfs.getMonths();
    }

    @Override
    public String format(int i) {
      final int idx = (i % 12);
      return months[idx];
    }
  }

  private static class ShortMonth implements NumberPicker.Formatter {
    private String [] months;

    public ShortMonth(DateFormatSymbols dfs) {
      months = dfs.getShortMonths();
    }

    @Override
    public String format(int i) {
      final int idx = (i % 12);
      return months[idx];
    }
  }

  private static class NumberMonth implements NumberPicker.Formatter {

    @Override
    public String format(int i) {
      final int idx = (i % 12);
      return String.format("%02d", idx + 1);
    }
  }

  private static class ShortNumberMonth implements NumberPicker.Formatter {

    @Override
    public String format(int i) {
      final int idx = (i % 12);
      return String.valueOf(idx + 1);
    }
  }
}

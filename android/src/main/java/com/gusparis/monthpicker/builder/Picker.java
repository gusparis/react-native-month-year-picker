package com.gusparis.monthpicker.builder;

import android.os.Handler;
import android.view.View;
import android.widget.NumberPicker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class Picker {
  private NumberPicker picker;
  private int counter = 0;
  private int incrementValue;
  private boolean isIncrement;

  private final Handler handler = new Handler();
  private final Runnable runnable = new Runnable() {
    @Override
    public void run() {
      startScrolling();
    }
  };

  Picker(View view) {
    this.picker = (NumberPicker) view;
  }

  public int getCounter() {
    return this.counter;
  }

  public NumberPicker getPicker() {
    return this.picker;
  }

  void run(int incrementValue) {
    this.isIncrement = incrementValue >= 0;
    this.incrementValue = incrementValue >= 0 ? incrementValue : -incrementValue;
    handler.postDelayed(runnable, 80);
  }

  public void incrementByOne(boolean increment) {
    try {
      final Method method = picker.getClass().getDeclaredMethod("changeValueByOne", boolean.class);
      method.setAccessible(true);
      method.invoke(picker, increment);

    } catch (final NoSuchMethodException | InvocationTargetException |
        IllegalAccessException | IllegalArgumentException e) {
      e.printStackTrace();
    }
  }

  private void startScrolling() {
    ++counter;
    if (counter > incrementValue) {
      counter = 0;
      return;
    }
    incrementByOne(isIncrement);
    handler.postDelayed(runnable, 80);
  }
}
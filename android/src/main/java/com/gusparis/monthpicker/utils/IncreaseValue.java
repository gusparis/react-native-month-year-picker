package com.gusparis.monthpicker.utils;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.widget.NumberPicker;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IncreaseValue {
  private int counter = 0;
  private final NumberPicker picker;
  private final int incrementValue;
  private final boolean increment;

  private final Handler handler = new Handler();
  private final Runnable fire = new Runnable() {
    @Override
    public void run() {
      fire();
    }
  };

  public IncreaseValue(final NumberPicker picker, final int incrementValue) {
    this.picker = picker;
    if (incrementValue > 0) {
      increment = true;
      this.incrementValue = incrementValue;
    } else {
      increment = false;
      this.incrementValue = -incrementValue;
    }
  }

  public void run(final int startDelay) {
    handler.postDelayed(fire, startDelay);  // This will execute the runnable passed to it (fire)
    // after [startDelay in milliseconds], ASYNCHRONOUSLY.
  }

  private void fire() {
    ++counter;
    if (counter > incrementValue) return;

    try {
      // refelction call for
      // picker.changeValueByOne(true);
      @SuppressLint("DiscouragedPrivateApi") final Method method = picker.getClass().getDeclaredMethod("changeValueByOne", boolean.class);
      method.setAccessible(true);
      method.invoke(picker, increment);

    } catch (final NoSuchMethodException | InvocationTargetException |
        IllegalAccessException | IllegalArgumentException ignored) {
    }

    handler.postDelayed(fire, 80);  // This will execute the runnable passed to it (fire)
    // after 120 milliseconds, ASYNCHRONOUSLY. Customize this value if necessary.
  }
}

package com.gusparis.monthpicker.builder;

import android.widget.NumberPicker;
import android.widget.NumberPicker.OnScrollListener;
import android.widget.NumberPicker.OnValueChangeListener;

import java.util.Observable;

class MonthYearScrollListener extends Observable implements OnScrollListener, OnValueChangeListener {
  private int currentScrollState;

  @Override
  public void onScrollStateChange(NumberPicker view, int scrollState) {
    currentScrollState = scrollState;
    if (scrollState == SCROLL_STATE_IDLE) {
      setChanged();
      notifyObservers(view);
    }
  }

  @Override
  public void onValueChange(NumberPicker view, int i, int i1) {
    if (currentScrollState == SCROLL_STATE_IDLE) {
      setChanged();
      notifyObservers(view);
    }
  }
}

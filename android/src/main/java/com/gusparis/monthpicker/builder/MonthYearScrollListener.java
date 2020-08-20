package com.gusparis.monthpicker.builder;

import android.widget.NumberPicker;
import android.widget.NumberPicker.OnScrollListener;

import java.util.Observable;

class MonthYearScrollListener extends Observable implements OnScrollListener {

  @Override
  public void onScrollStateChange(NumberPicker view, int scrollState) {
    if (scrollState == SCROLL_STATE_IDLE) {
      setChanged();
      notifyObservers(view);
    }
  }
}

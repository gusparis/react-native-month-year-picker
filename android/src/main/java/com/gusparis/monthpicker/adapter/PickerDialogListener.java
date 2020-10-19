package com.gusparis.monthpicker.adapter;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.widget.DatePicker;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;

import static com.gusparis.monthpicker.adapter.RNActions.ACTION_DATE_SET;
import static com.gusparis.monthpicker.adapter.RNActions.ACTION_DISMISSED;
import static com.gusparis.monthpicker.adapter.RNActions.ACTION_NEUTRAL;

class PickerDialogListener implements OnDateSetListener, OnDismissListener {

  private final Promise promise;
  private boolean promiseResolved = false;
  private ReactContext reactContext;

  PickerDialogListener(Promise promise, ReactContext reactContext) {
    this.promise = promise;
    this.reactContext = reactContext;
  }

  @Override
  public void onDateSet(DatePicker view, int year, int month, int flag) {
    if (!promiseResolved && reactContext.hasActiveCatalystInstance()) {
      WritableMap result = new WritableNativeMap();
      final RNActions action = flag == 0 ? ACTION_DATE_SET : ACTION_NEUTRAL;
      result.putString("action", action.value());
      result.putInt("year", year);
      result.putInt("month", month + 1);
      promise.resolve(result);
      promiseResolved = true;
    }
  }

  @Override
  public void onDismiss(DialogInterface dialog) {
    if (!promiseResolved && reactContext.hasActiveCatalystInstance()) {
      WritableMap result = new WritableNativeMap();
      result.putString("action", ACTION_DISMISSED.value());
      promise.resolve(result);
      promiseResolved = true;
    }
  }
}

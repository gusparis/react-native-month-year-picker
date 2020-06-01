package com.gusparis.monthpicker;

import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnClickListener;
import android.os.Build;
import android.widget.DatePicker;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.WritableNativeMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.gusparis.monthpicker.utils.IRNConstants;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.FragmentActivity;

@ReactModule(name = RNMPickerModule.REACT_CLASS)
public class RNMPickerModule extends ReactContextBaseJavaModule {

  private ReactApplicationContext reactContext;
  @VisibleForTesting
  public static final String REACT_CLASS = "RNMonthPicker";

  public RNMPickerModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;
  }

  @NonNull
  @Override
  public String getName() {
    return REACT_CLASS;
  }

  class DatePickerDialogListener implements OnDateSetListener, OnDismissListener, OnClickListener {

    private final Promise mPromise;
    private boolean mPromiseResolved = false;

    public DatePickerDialogListener(final Promise promise) {
      mPromise = promise;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
      if (!mPromiseResolved && getReactApplicationContext().hasActiveCatalystInstance()) {
        WritableMap result = new WritableNativeMap();
        result.putString("action", IRNConstants.ACTION_DATE_SET);
        result.putInt("year", year);
        result.putInt("month", month + 1);
        mPromise.resolve(result);
        mPromiseResolved = true;
      }
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
      if (!mPromiseResolved && getReactApplicationContext().hasActiveCatalystInstance()) {
        WritableMap result = new WritableNativeMap();
        result.putString("action", IRNConstants.ACTION_DISMISSED);
        mPromise.resolve(result);
        mPromiseResolved = true;
      }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
      if (!mPromiseResolved && getReactApplicationContext().hasActiveCatalystInstance()) {
        WritableMap result = new WritableNativeMap();
        result.putString("action", IRNConstants.ACTION_NEUTRAL_BUTTON);
        mPromise.resolve(result);
        mPromiseResolved = true;
      }
    }
  }

  @ReactMethod
  @RequiresApi(api = Build.VERSION_CODES.O)
  public void open(@Nullable ReadableMap options, Promise promise) {
    RNMonthPickerAdapter adapter = new RNMonthPickerAdapter(options);
    RNMonthPickerDialog mp = new RNMonthPickerDialog();
    mp.setListener(new DatePickerDialogListener(promise));
    mp.setAdapter(adapter);
    FragmentActivity fragmentActivity = (FragmentActivity) getCurrentActivity();
    assert fragmentActivity != null;
    mp.show(fragmentActivity.getSupportFragmentManager(), "RNMonthPicker");
  }
}

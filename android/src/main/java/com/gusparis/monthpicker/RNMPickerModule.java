package com.gusparis.monthpicker;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.UiThreadUtil;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.gusparis.monthpicker.adapter.RNMonthPickerProps;
import com.gusparis.monthpicker.adapter.RNPropsAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

@ReactModule(name = RNMPickerModule.REACT_CLASS)
public class RNMPickerModule extends ReactContextBaseJavaModule {

  protected ReactApplicationContext reactContext;
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

  @ReactMethod
  public void open(@Nullable final ReadableMap options, final Promise promise) {
    final FragmentActivity fragmentActivity = (FragmentActivity) getCurrentActivity();
    assert fragmentActivity != null;

    UiThreadUtil.runOnUiThread(new Runnable() {
      @Override
      public void run() {
        RNMonthPickerProps props = new RNPropsAdapter(options, promise, reactContext);
        RNMonthPickerDialog mp = new RNMonthPickerDialog(props);
        mp.show(fragmentActivity.getSupportFragmentManager(), REACT_CLASS);
      }
    });
  }
}

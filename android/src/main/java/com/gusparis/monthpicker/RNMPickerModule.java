package com.gusparis.monthpicker;

import android.os.Build;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.common.annotations.VisibleForTesting;
import com.facebook.react.module.annotations.ReactModule;
import com.gusparis.monthpicker.adapter.RNMonthPickerProps;
import com.gusparis.monthpicker.adapter.RNPropsAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
  @RequiresApi(api = Build.VERSION_CODES.O)
  public void open(@Nullable ReadableMap options, Promise promise) {
    FragmentActivity fragmentActivity = (FragmentActivity) getCurrentActivity();
    assert fragmentActivity != null;

    RNMonthPickerProps props = new RNPropsAdapter(options, promise, reactContext);
    RNMonthPickerDialog mp = new RNMonthPickerDialog(props);
    mp.show(fragmentActivity.getSupportFragmentManager(), REACT_CLASS);
  }
}

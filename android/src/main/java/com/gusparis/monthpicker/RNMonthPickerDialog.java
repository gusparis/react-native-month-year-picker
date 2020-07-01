package com.gusparis.monthpicker;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import com.gusparis.monthpicker.adapter.RNMonthPickerProps;
import com.gusparis.monthpicker.builder.PickerViewFactory;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

public class RNMonthPickerDialog extends DialogFragment {
  private RNMonthPickerProps props;

  public RNMonthPickerDialog(RNMonthPickerProps props) {
    this.props = props;
  }

  @Override
  public void onDismiss(DialogInterface dialog) {
    props.onChange();
  }

  @Override
  @RequiresApi(api = Build.VERSION_CODES.O)
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    PickerViewFactory pickerViewFactory = new PickerViewFactory(props, this);
    return pickerViewFactory.build();
  }
}

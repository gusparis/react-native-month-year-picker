package com.gusparis.monthpicker;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.NumberPicker;

import com.gusparis.monthpicker.utils.IRNConstants;
import com.gusparis.monthpicker.utils.OnScrollListener;

import java.text.DateFormatSymbols;
import java.util.Objects;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

public class RNMonthPickerDialog extends DialogFragment {
  private RNMPickerModule.DatePickerDialogListener listener;
  private RNMonthPickerAdapter adapter;

  public void setListener(RNMPickerModule.DatePickerDialogListener listener) {
    this.listener = listener;
  }

  public void setAdapter(RNMonthPickerAdapter adapter) {
    this.adapter = adapter;
  }


  @Override
  public void onDismiss(DialogInterface dialog) {
    listener.onDismiss(dialog);
  }

  @Override
  @RequiresApi(api = Build.VERSION_CODES.O)
  public Dialog onCreateDialog(Bundle savedInstanceState) {
    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
    // Get the layout inflater
    LayoutInflater inflater = Objects.requireNonNull(getActivity()).getLayoutInflater();

    View dialog = inflater.inflate(R.layout.picker_list_view, null);
    final NumberPicker monthPicker = dialog.findViewById(R.id.month_picker);
    final NumberPicker yearPicker = dialog.findViewById(R.id.year_picker);

    final OnScrollListener scrollListener = new OnScrollListener(
        monthPicker,
        yearPicker,
        adapter.getMinimumValue(),
        adapter.getMaximumValue()
    );

    final String[] months = new DateFormatSymbols().getShortMonths();

    monthPicker.setMinValue(0);
    monthPicker.setMaxValue(11);
    monthPicker.setDisplayedValues(months);
    monthPicker.setWrapSelectorWheel(false);
    monthPicker.setValue(adapter.getValue().getMonthValue() - 1);

    monthPicker.setOnScrollListener(scrollListener);

    int year = adapter.getValue().getYear();
    yearPicker.setMinValue(year - IRNConstants.DEFAULT_SIZE);
    yearPicker.setMaxValue(year + IRNConstants.DEFAULT_SIZE);
    yearPicker.setValue(year);

    yearPicker.setOnScrollListener(scrollListener);

    builder.setView(dialog)
        .setPositiveButton(adapter.getOkButton(), new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int id) {
            listener.onDateSet(null, yearPicker.getValue(), monthPicker.getValue(), 0);
            RNMonthPickerDialog.this.getDialog().cancel();
          }
        })
        .setNegativeButton(adapter.getCancelButton(), new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int id) {
            RNMonthPickerDialog.this.getDialog().cancel();
          }
        });
    return builder.create();
  }
}

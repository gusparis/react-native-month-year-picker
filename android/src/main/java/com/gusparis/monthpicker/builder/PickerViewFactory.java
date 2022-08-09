package com.gusparis.monthpicker.builder;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;

import com.gusparis.monthpicker.R;
import com.gusparis.monthpicker.RNMonthPickerDialog;
import com.gusparis.monthpicker.adapter.RNMonthPickerProps;

import androidx.fragment.app.FragmentActivity;

public class PickerViewFactory {

  private static final int DARK_VIEW = R.layout.picker_list_view_dark;
  private static final int LIGHT_VIEW = R.layout.picker_list_view;
  private static final int DARK_STYLE = R.style.DarkStyle ;
  private static final int LIGHT_STYLE = R.style.LightStyle;

  private RNMonthPickerProps props;
  private RNMonthPickerDialog rnMonthPickerDialog;

  public PickerViewFactory(RNMonthPickerProps props, RNMonthPickerDialog rnMonthPickerDialog) {
    this.props = props;
    this.rnMonthPickerDialog = rnMonthPickerDialog;
  }

  public AlertDialog build() {
    if (rnMonthPickerDialog.getActivity() == null) {
      throw new NullPointerException();
    }
    final FragmentActivity fragmentActivity = rnMonthPickerDialog.getActivity();
    final int uiMode = fragmentActivity.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
    final int dialogStyle = uiMode == Configuration.UI_MODE_NIGHT_YES && props.autoTheme()
        ? DARK_STYLE : LIGHT_STYLE;
    final int contentViewStyle = uiMode == Configuration.UI_MODE_NIGHT_YES && props.autoTheme()
        ? DARK_VIEW : LIGHT_VIEW;

    AlertDialog.Builder builder = new AlertDialog.Builder(rnMonthPickerDialog.getActivity(), dialogStyle);
    LayoutInflater inflater = fragmentActivity.getLayoutInflater();
    View contentView = inflater.inflate(contentViewStyle, null);

    MonthYearScrollListener scrollListener = new MonthYearScrollListener();
    final MonthYearNumberPicker monthPicker = new MonthNumberPicker()
        .view(contentView)
        .props(props)
        .onScrollListener(scrollListener)
        .build();
    final MonthYearNumberPicker yearPicker = new YearNumberPicker()
        .view(contentView)
        .props(props)
        .onScrollListener(scrollListener)
        .build();

    scrollListener.addObserver(monthPicker);
    scrollListener.addObserver(yearPicker);

    builder.setView(contentView)
        .setPositiveButton(props.okButton(), new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int id) {
            props.onChange(yearPicker.getValue(), monthPicker.getValue(), 0);
            rnMonthPickerDialog.getDialog().cancel();
          }
        })
        .setNegativeButton(props.cancelButton(), new DialogInterface.OnClickListener() {
          @Override
          public void onClick(DialogInterface dialog, int id) {
            rnMonthPickerDialog.getDialog().cancel();
          }
        });

    if (props.neutralButton() != null) {
      builder.setView(contentView)
          .setNeutralButton(props.neutralButton(), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
              props.onChange(yearPicker.getValue(), monthPicker.getValue(), 1);
              rnMonthPickerDialog.getDialog().cancel();
            }
          });
    }

    return builder.create();
  }
}

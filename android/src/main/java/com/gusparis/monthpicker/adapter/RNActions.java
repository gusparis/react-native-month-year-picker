package com.gusparis.monthpicker.adapter;

public enum RNActions {

  ACTION_DATE_SET("dateSetAction"),
  ACTION_DISMISSED("dismissedAction");

  private String value;

  RNActions(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}

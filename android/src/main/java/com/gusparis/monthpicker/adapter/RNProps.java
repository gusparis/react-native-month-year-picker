package com.gusparis.monthpicker.adapter;

public enum RNProps {
  VALUE("value"),
  MAXIMUM_VALUE("maximumDate"),
  MINIMUM_VALUE("minimumDate"),
  ENABLE_AUTO_DARK_MODE("enableAutoDarkMode"),
  OK_BUTTON("okButton"),
  CANCEL_BUTTON("cancelButton");

  private String value;

  RNProps(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}

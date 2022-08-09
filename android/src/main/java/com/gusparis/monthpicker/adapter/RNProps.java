package com.gusparis.monthpicker.adapter;

public enum RNProps {
  VALUE("value"),
  MAXIMUM_VALUE("maximumDate"),
  MINIMUM_VALUE("minimumDate"),
  OK_BUTTON("okButton"),
  CANCEL_BUTTON("cancelButton"),
  NEUTRAL_BUTTON("neutralButton"),
  LOCALE("locale"),
  MODE("mode"),
  AUTO_THEME("autoTheme");

  private String value;

  RNProps(String value) {
    this.value = value;
  }

  public String value() {
    return value;
  }
}

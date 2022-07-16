package com.isystk.sample.common.values;

/**
 * 性別
 */
public enum Gender implements Values {

  WOMAN("1", "女性"),
  MAN("2", "男性");

  private final String code;
  private final String text;

  private Gender(String code, String text) {
    this.code = code;
    this.text = text;
  }

  /**
   * コードに一致する値を取得します。
   *
   * @param code
   * @return
   */
  public static Gender getValue(String code) {
    if (code == null) {
      return null;
    }
    for (Gender div : values()) {
      if (div.getCode().equals(code)) {
        return div;
      }
    }
    return null;
  }

  public String getCode() {
    return this.code;
  }

  public String getText() {
    return this.text;
  }
}
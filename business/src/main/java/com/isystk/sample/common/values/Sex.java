package com.isystk.sample.common.values;

/**
 * 性別
 */
public enum Sex implements Values {

  WOMAN(1, "女性"),
  MAN(2, "男性");

  private Sex(int code, String text) {
    this.code = code;
    this.text = text;
  }

  private final Integer code;
  private final String text;

  private Sex(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  /**
   * コードに一致する値を取得します。
   *
   * @param code
   * @return
   */
  public static Sex getValue(Integer code) {
    if (code == null) {
      return null;
    }
    for (Sex div : values()) {
      if (div.getCode().equals(code)) {
        return div;
      }
    }
    return null;
  }

  public Integer getCode() {
    return this.code;
  }

  public String getText() {
    return this.text;
  }
}
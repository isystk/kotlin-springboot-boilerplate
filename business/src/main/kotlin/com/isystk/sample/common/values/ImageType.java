package com.isystk.sample.common.values;

/**
 * 画像タイプ
 */
public enum ImageType implements Values {

  UNKNOWN("0", "不明"),
  STOCK("1", "商品"),
  CONTACT("2", "お問い合わせ");

  private final String code;
  private final String text;

  private ImageType(String code, String text) {
    this.code = code;
    this.text = text;
  }

  /**
   * コードに一致する値を取得します。
   *
   * @param code
   * @return
   */
  public static ImageType getValue(String code) {
    if (code == null) {
      return null;
    }
    for (ImageType div : values()) {
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
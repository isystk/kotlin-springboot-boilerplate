package com.isystk.sample.common.values;

/**
 * メールテンプレート区分
 */
public enum MailTemplateGroup implements Values {

  ENTRY_REGIST("1", "会員登録"),

  STOCK_PAYMENT("2", "商品決算");

  private final String code;
  private final String text;

  private MailTemplateGroup(String code, String text) {
    this.code = code;
    this.text = text;
  }

  /**
   * コードに一致する値を取得します。
   *
   * @param code
   * @return
   */
  public static MailTemplateGroup getValue(Long code) {
    if (code == null) {
      return null;
    }
    for (MailTemplateGroup div : values()) {
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
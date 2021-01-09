package com.isystk.sample.common.values;

/**
 * メールテンプレート区分
 */
public enum MailTemplateDiv implements Values {

  ENTRY_REGIST(1, "会員登録");

  private MailTemplateDiv(int code, String text) {
    this.code = code;
    this.text = text;
  }

  private final Integer code;
  private final String text;

  private MailTemplateDiv(Integer code, String text) {
    this.code = code;
    this.text = text;
  }

  /**
   * コードに一致する値を取得します。
   *
   * @param code
   * @return
   */
  public static MailTemplateDiv getValue(Integer code) {
    if (code == null) {
      return null;
    }
    for (MailTemplateDiv div : values()) {
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
package com.isystk.sample.common.values;

/**
 * メールテンプレート
 */
public enum MailTemplateDiv implements Values {

  ENTRY_REGIST_TEMPORARY("1", "仮会員登録通知メール", MailTemplateGroup.ENTRY_REGIST),
  ENTRY_REGIST_VALID("2", "本登録完了通知メール", MailTemplateGroup.ENTRY_REGIST),
  ENTRY_REMIND("3", "新パスワード設定画面のお知らせメール", MailTemplateGroup.ENTRY_REGIST),
  STOCK_PAYMENT_COMPLETE("4", "商品購入完了通知メール", MailTemplateGroup.STOCK_PAYMENT);

  private final String code;
  private final String text;
  private final MailTemplateGroup div;

  private MailTemplateDiv(String code, String text, MailTemplateGroup div) {
    this.code = code;
    this.text = text;
    this.div = div;
  }

  /**
   * コードに一致する値を取得します。
   *
   * @param code
   * @return
   */
  public static MailTemplateDiv getValue(String code) {
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

  public String getCode() {
    return this.code;
  }

  public String getText() {
    return this.text;
  }

  public MailTemplateGroup getDiv() {
    return this.div;
  }
}
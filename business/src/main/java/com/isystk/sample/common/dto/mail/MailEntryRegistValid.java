package com.isystk.sample.common.dto.mail;

/**
 * 本会員登録完了メール
 */
public class MailEntryRegistValid {

  String userName;
  String domain;

  public String getUserName() {
    return this.userName;
  }

  public String getDomain() {
    return this.domain;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }
}

package com.isystk.sample.common.dto.mail;

/**
 * 仮会員登録メール
 */
public class MailEntryRegistTemporary {

  String userName;
  String domain;
  String onetimeKey;

  public String getUserName() {
    return this.userName;
  }

  public String getDomain() {
    return this.domain;
  }

  public String getOnetimeKey() {
    return this.onetimeKey;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public void setOnetimeKey(String onetimeKey) {
    this.onetimeKey = onetimeKey;
  }
}

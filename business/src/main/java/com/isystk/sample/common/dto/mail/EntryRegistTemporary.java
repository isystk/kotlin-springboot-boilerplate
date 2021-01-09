package com.isystk.sample.common.dto.mail;

/**
 * 仮会員登録メール
 */
public class EntryRegistTemporary {

  String familyName;
  String domain;
  String onetimeKey;

  public String getFamilyName() {
    return this.familyName;
  }

  public String getDomain() {
    return this.domain;
  }

  public String getOnetimeKey() {
    return this.onetimeKey;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }

  public void setOnetimeKey(String onetimeKey) {
    this.onetimeKey = onetimeKey;
  }
}

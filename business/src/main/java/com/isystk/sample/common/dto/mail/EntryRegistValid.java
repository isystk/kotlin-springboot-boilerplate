package com.isystk.sample.common.dto.mail;

/**
 * 本会員登録完了メール
 */
public class EntryRegistValid {

  String familyName;
  String domain;

  public String getFamilyName() {
    return this.familyName;
  }

  public String getDomain() {
    return this.domain;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public void setDomain(String domain) {
    this.domain = domain;
  }
}

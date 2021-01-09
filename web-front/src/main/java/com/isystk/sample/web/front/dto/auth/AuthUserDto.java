package com.isystk.sample.web.front.dto.auth;

import com.isystk.sample.common.dto.Dto;

public class AuthUserDto implements Dto {

  /**
   * 会員ID
   */
  Integer userId;

  /**
   * 姓
   */
  String familyName;

  /**
   * 名
   */
  String name;

  /**
   * セッションID
   */
  String sessionId;

  public Integer getUserId() {
    return this.userId;
  }

  public String getFamilyName() {
    return this.familyName;
  }

  public String getName() {
    return this.name;
  }

  public String getSessionId() {
    return this.sessionId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public void setFamilyName(String familyName) {
    this.familyName = familyName;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }
}

package com.isystk.sample.web.front.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isystk.sample.common.dto.Dto;
import java.math.BigInteger;
import java.time.LocalDateTime;

public class AuthUserDto implements Dto {

  /** ユーザID */
  BigInteger id;

  /** プロバイダID */
  @JsonProperty("provider_id")
  String providerId;

  /** プロバイダ名 */
  @JsonProperty("provider_name")
  String providerName;

  /** ユーザ名 */
  String name;

  /** メールアドレス */
  String email;

  /** メール検証日時 */
  @JsonProperty("email_verified_at")
  LocalDateTime emailVerifiedAt;

  /** 登録日時 */
  @JsonProperty("created_at")
  LocalDateTime createdAt;

  /** 更新日時 */
  @JsonProperty("updated_at")
  LocalDateTime updatedAt;

  /**
   * セッションID
   */
  @JsonProperty("session_id")
  String sessionId;

  public BigInteger getId() {
    return this.id;
  }

  public String getProviderId() {
    return this.providerId;
  }

  public String getProviderName() {
    return this.providerName;
  }

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public LocalDateTime getEmailVerifiedAt() {
    return this.emailVerifiedAt;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return this.updatedAt;
  }

  public String getSessionId() {
    return this.sessionId;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  @JsonProperty("provider_id")
  public void setProviderId(String providerId) {
    this.providerId = providerId;
  }

  @JsonProperty("provider_name")
  public void setProviderName(String providerName) {
    this.providerName = providerName;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  @JsonProperty("email_verified_at")
  public void setEmailVerifiedAt(LocalDateTime emailVerifiedAt) {
    this.emailVerifiedAt = emailVerifiedAt;
  }

  @JsonProperty("created_at")
  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  @JsonProperty("updated_at")
  public void setUpdatedAt(LocalDateTime updatedAt) {
    this.updatedAt = updatedAt;
  }

  @JsonProperty("session_id")
  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }
}

package com.isystk.sample.web.base.controller.api.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FieldErrorResource {

  // 項目名
  String fieldName;

  // エラー種別
  String errorType;

  // エラーメッセージ
  String errorMessage;

  public FieldErrorResource() {
  }

  public String getFieldName() {
    return this.fieldName;
  }

  public String getErrorType() {
    return this.errorType;
  }

  public String getErrorMessage() {
    return this.errorMessage;
  }

  public void setFieldName(String fieldName) {
    this.fieldName = fieldName;
  }

  public void setErrorType(String errorType) {
    this.errorType = errorType;
  }

  public void setErrorMessage(String errorMessage) {
    this.errorMessage = errorMessage;
  }

  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof FieldErrorResource)) {
      return false;
    }
    final FieldErrorResource other = (FieldErrorResource) o;
    if (!other.canEqual((Object) this)) {
      return false;
    }
    final Object this$fieldName = this.getFieldName();
    final Object other$fieldName = other.getFieldName();
    if (this$fieldName == null ? other$fieldName != null
        : !this$fieldName.equals(other$fieldName)) {
      return false;
    }
    final Object this$errorType = this.getErrorType();
    final Object other$errorType = other.getErrorType();
    if (this$errorType == null ? other$errorType != null
        : !this$errorType.equals(other$errorType)) {
      return false;
    }
    final Object this$errorMessage = this.getErrorMessage();
    final Object other$errorMessage = other.getErrorMessage();
    if (this$errorMessage == null ? other$errorMessage != null
        : !this$errorMessage.equals(other$errorMessage)) {
      return false;
    }
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof FieldErrorResource;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $fieldName = this.getFieldName();
    result = result * PRIME + ($fieldName == null ? 43 : $fieldName.hashCode());
    final Object $errorType = this.getErrorType();
    result = result * PRIME + ($errorType == null ? 43 : $errorType.hashCode());
    final Object $errorMessage = this.getErrorMessage();
    result = result * PRIME + ($errorMessage == null ? 43 : $errorMessage.hashCode());
    return result;
  }

  public String toString() {
    return "FieldErrorResource(fieldName=" + this.getFieldName() + ", errorType="
        + this.getErrorType() + ", errorMessage=" + this.getErrorMessage() + ")";
  }
}

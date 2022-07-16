package com.isystk.sample.web.base.controller.api.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResourceImpl extends ResourceImpl {

  // リクエストID
  String requestId;

  // 入力エラー
  List<FieldErrorResource> fieldErrors;

  public ErrorResourceImpl() {
  }

  public String getRequestId() {
    return this.requestId;
  }

  public List<FieldErrorResource> getFieldErrors() {
    return this.fieldErrors;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public void setFieldErrors(List<FieldErrorResource> fieldErrors) {
    this.fieldErrors = fieldErrors;
  }

  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof ErrorResourceImpl)) {
      return false;
    }
    final ErrorResourceImpl other = (ErrorResourceImpl) o;
    if (!other.canEqual((Object) this)) {
      return false;
    }
    if (!super.equals(o)) {
      return false;
    }
    final Object this$requestId = this.getRequestId();
    final Object other$requestId = other.getRequestId();
    if (this$requestId == null ? other$requestId != null
        : !this$requestId.equals(other$requestId)) {
      return false;
    }
    final Object this$fieldErrors = this.getFieldErrors();
    final Object other$fieldErrors = other.getFieldErrors();
    if (this$fieldErrors == null ? other$fieldErrors != null
        : !this$fieldErrors.equals(other$fieldErrors)) {
      return false;
    }
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof ErrorResourceImpl;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = super.hashCode();
    final Object $requestId = this.getRequestId();
    result = result * PRIME + ($requestId == null ? 43 : $requestId.hashCode());
    final Object $fieldErrors = this.getFieldErrors();
    result = result * PRIME + ($fieldErrors == null ? 43 : $fieldErrors.hashCode());
    return result;
  }
}

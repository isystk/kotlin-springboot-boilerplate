package com.isystk.sample.web.base.controller.api.resource;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.isystk.sample.common.dto.Dto;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResourceImpl implements Resource {

  List<? extends Dto> data;

  // メッセージ
  String message;

  public ResourceImpl() {
  }

  public List<? extends Dto> getData() {
    return this.data;
  }

  public String getMessage() {
    return this.message;
  }

  public void setData(List<? extends Dto> data) {
    this.data = data;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof ResourceImpl)) {
      return false;
    }
    final ResourceImpl other = (ResourceImpl) o;
    if (!other.canEqual((Object) this)) {
      return false;
    }
    final Object this$data = this.getData();
    final Object other$data = other.getData();
    if (this$data == null ? other$data != null : !this$data.equals(other$data)) {
      return false;
    }
    final Object this$message = this.getMessage();
    final Object other$message = other.getMessage();
    if (this$message == null ? other$message != null : !this$message.equals(other$message)) {
      return false;
    }
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof ResourceImpl;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $data = this.getData();
    result = result * PRIME + ($data == null ? 43 : $data.hashCode());
    final Object $message = this.getMessage();
    result = result * PRIME + ($message == null ? 43 : $message.hashCode());
    return result;
  }

  public String toString() {
    return "ResourceImpl(data=" + this.getData() + ", message=" + this.getMessage() + ")";
  }
}

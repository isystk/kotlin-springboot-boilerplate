package com.isystk.sample.web.front.controller.api.v1.member.post;

import java.io.Serializable;
import javax.validation.constraints.Digits;

public class MemberPostDetailImageRestForm implements Serializable {

  @Digits(integer = 9, fraction = 0)
  Integer imageId;

  public @Digits(integer = 9, fraction = 0) Integer getImageId() {
    return this.imageId;
  }

  public void setImageId(@Digits(integer = 9, fraction = 0) Integer imageId) {
    this.imageId = imageId;
  }
}

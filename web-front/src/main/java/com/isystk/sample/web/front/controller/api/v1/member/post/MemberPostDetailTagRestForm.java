package com.isystk.sample.web.front.controller.api.v1.member.post;

import java.io.Serializable;
import javax.validation.constraints.Digits;

public class MemberPostDetailTagRestForm implements Serializable {

  @Digits(integer = 9, fraction = 0)
  Integer tagId;

  public @Digits(integer = 9, fraction = 0) Integer getTagId() {
    return this.tagId;
  }

  public void setTagId(@Digits(integer = 9, fraction = 0) Integer tagId) {
    this.tagId = tagId;
  }
}

package com.isystk.sample.web.front.controller.api.v1.member.post;

import com.isystk.sample.common.dto.Pageable;
import com.isystk.sample.web.base.controller.html.BaseSearchForm;

public class MemberPostRestForm extends BaseSearchForm implements Pageable {

  private static final long serialVersionUID = 7593564324192730932L;

  Integer postId;

  public Integer getPostId() {
    return this.postId;
  }

  public void setPostId(Integer postId) {
    this.postId = postId;
  }
}
package com.isystk.sample.web.admin.controller.html.post.tag;

import com.isystk.sample.common.dto.Pageable;
import com.isystk.sample.web.base.controller.html.BaseSearchForm;

public class PostTagSearchForm extends BaseSearchForm implements Pageable {

  private static final long serialVersionUID = 7593564324192730932L;

  String postTagName;

  public String getPostTagName() {
    return this.postTagName;
  }

  public void setPostTagName(String postTagName) {
    this.postTagName = postTagName;
  }
}

package com.isystk.sample.web.front.dto;

import com.isystk.sample.common.dto.Dto;

/**
 * 投稿フロント表示用 投稿画像DTO
 */
public class FrontPostTagDto implements Dto {

  Integer tagId;

  String tagName;

  public Integer getTagId() {
    return this.tagId;
  }

  public String getTagName() {
    return this.tagName;
  }

  public void setTagId(Integer tagId) {
    this.tagId = tagId;
  }

  public void setTagName(String tagName) {
    this.tagName = tagName;
  }
}

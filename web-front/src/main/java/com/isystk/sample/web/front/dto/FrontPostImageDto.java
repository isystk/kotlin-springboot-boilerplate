package com.isystk.sample.web.front.dto;

import com.isystk.sample.common.dto.Dto;

/**
 * 投稿フロント表示用 投稿画像DTO
 */
public class FrontPostImageDto implements Dto {

  Integer imageId;

  String imageUrl;

  public Integer getImageId() {
    return this.imageId;
  }

  public String getImageUrl() {
    return this.imageUrl;
  }

  public void setImageId(Integer imageId) {
    this.imageId = imageId;
  }

  public void setImageUrl(String imageUrl) {
    this.imageUrl = imageUrl;
  }
}

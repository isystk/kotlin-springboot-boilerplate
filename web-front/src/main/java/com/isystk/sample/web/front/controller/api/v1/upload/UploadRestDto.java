package com.isystk.sample.web.front.controller.api.v1.upload;

import com.isystk.sample.common.dto.Dto;

public class UploadRestDto implements Dto {

  Integer imageId;

  Integer imagePath;

  public Integer getImageId() {
    return this.imageId;
  }

  public Integer getImagePath() {
    return this.imagePath;
  }

  public void setImageId(Integer imageId) {
    this.imageId = imageId;
  }

  public void setImagePath(Integer imagePath) {
    this.imagePath = imagePath;
  }
}

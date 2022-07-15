package com.isystk.sample.web.admin.dto;

import com.isystk.sample.common.values.ImageType;

public class PhotoSearchResultDto {

  ImageType imageType;

  String imageName;

  public ImageType getImageType() {
    return this.imageType;
  }

  public String getImageName() {
    return this.imageName;
  }

  public void setImageType(ImageType imageType) {
    this.imageType = imageType;
  }

  public void setImageName(String imageName) {
    this.imageName = imageName;
  }
}
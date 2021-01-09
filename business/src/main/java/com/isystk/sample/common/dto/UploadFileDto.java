package com.isystk.sample.common.dto;

public class UploadFileDto implements Dto {

  Integer imageId;

  String imageUrlSquare;

  String imageUrlSd;

  public Integer getImageId() {
    return this.imageId;
  }

  public String getImageUrlSquare() {
    return this.imageUrlSquare;
  }

  public String getImageUrlSd() {
    return this.imageUrlSd;
  }

  public void setImageId(Integer imageId) {
    this.imageId = imageId;
  }

  public void setImageUrlSquare(String imageUrlSquare) {
    this.imageUrlSquare = imageUrlSquare;
  }

  public void setImageUrlSd(String imageUrlSd) {
    this.imageUrlSd = imageUrlSd;
  }
}

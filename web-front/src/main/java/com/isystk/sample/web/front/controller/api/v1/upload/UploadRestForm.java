package com.isystk.sample.web.front.controller.api.v1.upload;

import com.isystk.sample.web.base.controller.html.BaseForm;
import org.springframework.web.multipart.MultipartFile;

public class UploadRestForm extends BaseForm {

  private static final long serialVersionUID = 7593564324192730932L;

  Integer imageId;

  MultipartFile imageFile;

  public Integer getImageId() {
    return this.imageId;
  }

  public MultipartFile getImageFile() {
    return this.imageFile;
  }

  public void setImageId(Integer imageId) {
    this.imageId = imageId;
  }

  public void setImageFile(MultipartFile imageFile) {
    this.imageFile = imageFile;
  }
}

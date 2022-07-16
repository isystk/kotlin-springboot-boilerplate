package com.isystk.sample.domain.dto;

import com.isystk.sample.domain.entity.ContactFormImage;

public class ContactFormImageRepositoryDto extends ContactFormImage {

  String contactImageName;

  String contactImageData;

  public String getContactImageName() {
    return this.contactImageName;
  }

  public String getContactImageData() {
    return this.contactImageData;
  }

  public void setContactImageName(String contactImageName) {
    this.contactImageName = contactImageName;
  }

  public void setContactImageData(String contactImageData) {
    this.contactImageData = contactImageData;
  }
}
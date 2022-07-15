package com.isystk.sample.domain.dto;

import com.isystk.sample.domain.entity.ContactForm;
import java.util.List;

public class ContactFormRepositoryDto extends ContactForm {

  List<ContactFormImageRepositoryDto> imageList;

  public List<ContactFormImageRepositoryDto> getImageList() {
    return this.imageList;
  }

  public void setImageList(List<ContactFormImageRepositoryDto> imageList) {
    this.imageList = imageList;
  }
}
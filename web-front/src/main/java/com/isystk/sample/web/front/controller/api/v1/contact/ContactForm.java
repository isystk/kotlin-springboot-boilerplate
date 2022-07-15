package com.isystk.sample.web.front.controller.api.v1.contact;

import com.isystk.sample.common.dto.Pageable;
import com.isystk.sample.web.base.controller.html.BaseSearchForm;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

public class ContactForm extends BaseSearchForm implements Pageable {

  private static final long serialVersionUID = 7593564324192730932L;

  @NotBlank
  String yourName;

  @NotBlank
  String email;

  @NotBlank
  @NumberFormat
  String gender;

  @NotBlank
  @NumberFormat
  String age;

  @NotBlank
  String title;

  @NotBlank
  String contact;

  String url;

  @NotBlank
  String contactImageName;

  @NotBlank
  String contactImageData;

  public @NotBlank String getYourName() {
    return this.yourName;
  }

  public @NotBlank String getEmail() {
    return this.email;
  }

  public @NotBlank String getGender() {
    return this.gender;
  }

  public @NotBlank String getAge() {
    return this.age;
  }

  public @NotBlank String getTitle() {
    return this.title;
  }

  public @NotBlank String getContact() {
    return this.contact;
  }

  public String getUrl() {
    return this.url;
  }

  public @NotBlank String getContactImageName() {
    return this.contactImageName;
  }

  public @NotBlank String getContactImageData() {
    return this.contactImageData;
  }

  public void setYourName(@NotBlank String yourName) {
    this.yourName = yourName;
  }

  public void setEmail(@NotBlank String email) {
    this.email = email;
  }

  public void setGender(@NotBlank String gender) {
    this.gender = gender;
  }

  public void setAge(@NotBlank String age) {
    this.age = age;
  }

  public void setTitle(@NotBlank String title) {
    this.title = title;
  }

  public void setContact(@NotBlank String contact) {
    this.contact = contact;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public void setContactImageName(@NotBlank String contactImageName) {
    this.contactImageName = contactImageName;
  }

  public void setContactImageData(@NotBlank String contactImageData) {
    this.contactImageData = contactImageData;
  }
}

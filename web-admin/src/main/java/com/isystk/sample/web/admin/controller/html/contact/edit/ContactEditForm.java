package com.isystk.sample.web.admin.controller.html.contact.edit;

import com.isystk.sample.web.base.controller.html.BaseForm;
import java.math.BigInteger;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public class ContactEditForm extends BaseForm {

  private static final long serialVersionUID = 7593564324192730932L;

  @NotNull
  BigInteger contactId;

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

  @NotBlank
  String url;

  @NotBlank
  String contactImageName;

  @NotBlank
  String contactImageData;

  public @NotNull BigInteger getContactId() {
    return this.contactId;
  }

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

  public @NotBlank String getUrl() {
    return this.url;
  }

  public @NotBlank String getContactImageName() {
    return this.contactImageName;
  }

  public @NotBlank String getContactImageData() {
    return this.contactImageData;
  }

  public void setContactId(@NotNull BigInteger contactId) {
    this.contactId = contactId;
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

  public void setUrl(@NotBlank String url) {
    this.url = url;
  }

  public void setContactImageName(@NotBlank String contactImageName) {
    this.contactImageName = contactImageName;
  }

  public void setContactImageData(@NotBlank String contactImageData) {
    this.contactImageData = contactImageData;
  }
}

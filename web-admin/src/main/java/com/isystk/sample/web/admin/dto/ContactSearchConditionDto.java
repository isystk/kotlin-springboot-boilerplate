package com.isystk.sample.web.admin.dto;

import java.time.LocalDate;

public class ContactSearchConditionDto {

  String userName;

  LocalDate createdAtFrom;

  LocalDate createdAtTo;

  public String getUserName() {
    return this.userName;
  }

  public LocalDate getCreatedAtFrom() {
    return this.createdAtFrom;
  }

  public LocalDate getCreatedAtTo() {
    return this.createdAtTo;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public void setCreatedAtFrom(LocalDate createdAtFrom) {
    this.createdAtFrom = createdAtFrom;
  }

  public void setCreatedAtTo(LocalDate createdAtTo) {
    this.createdAtTo = createdAtTo;
  }
}
package com.isystk.sample.web.admin.dto;

import java.time.LocalDate;

public class UserSearchConditionDto {

  String name;

  String email;

  LocalDate createdAtFrom;

  LocalDate createdAtTo;

  public String getName() {
    return this.name;
  }

  public String getEmail() {
    return this.email;
  }

  public LocalDate getCreatedAtFrom() {
    return this.createdAtFrom;
  }

  public LocalDate getCreatedAtTo() {
    return this.createdAtTo;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setCreatedAtFrom(LocalDate createdAtFrom) {
    this.createdAtFrom = createdAtFrom;
  }

  public void setCreatedAtTo(LocalDate createdAtTo) {
    this.createdAtTo = createdAtTo;
  }
}
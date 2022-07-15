package com.isystk.sample.web.admin.controller.html.user;

import com.isystk.sample.common.dto.Pageable;
import com.isystk.sample.web.base.controller.html.BaseSearchForm;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class UserListForm extends BaseSearchForm implements Pageable {

  private static final long serialVersionUID = 7593564324192730932L;

  String name;

  String email;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDate createdAtFrom;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
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

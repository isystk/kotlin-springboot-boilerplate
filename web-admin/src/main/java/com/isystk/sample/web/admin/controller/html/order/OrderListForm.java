package com.isystk.sample.web.admin.controller.html.order;

import com.isystk.sample.common.dto.Pageable;
import com.isystk.sample.web.base.controller.html.BaseSearchForm;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

public class OrderListForm extends BaseSearchForm implements Pageable {

  private static final long serialVersionUID = 7593564324192730932L;

  String userName;

  String stockName;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDate createdAtFrom;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDate createdAtTo;

  public String getUserName() {
    return this.userName;
  }

  public String getStockName() {
    return this.stockName;
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

  public void setStockName(String stockName) {
    this.stockName = stockName;
  }

  public void setCreatedAtFrom(LocalDate createdAtFrom) {
    this.createdAtFrom = createdAtFrom;
  }

  public void setCreatedAtTo(LocalDate createdAtTo) {
    this.createdAtTo = createdAtTo;
  }
}

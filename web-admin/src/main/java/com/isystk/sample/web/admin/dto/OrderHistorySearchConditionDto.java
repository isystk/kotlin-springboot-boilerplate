package com.isystk.sample.web.admin.dto;

import java.time.LocalDate;

public class OrderHistorySearchConditionDto {

  String userName;

  String stockName;

  LocalDate createdAtFrom;

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
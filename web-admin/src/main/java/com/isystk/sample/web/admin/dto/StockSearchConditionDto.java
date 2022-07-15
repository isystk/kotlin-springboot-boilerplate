package com.isystk.sample.web.admin.dto;

import java.math.BigInteger;
import java.time.LocalDate;

public class StockSearchConditionDto {

  BigInteger stockId;

  String name;

  LocalDate createdAtFrom;

  LocalDate createdAtTo;

  public BigInteger getStockId() {
    return this.stockId;
  }

  public String getName() {
    return this.name;
  }

  public LocalDate getCreatedAtFrom() {
    return this.createdAtFrom;
  }

  public LocalDate getCreatedAtTo() {
    return this.createdAtTo;
  }

  public void setStockId(BigInteger stockId) {
    this.stockId = stockId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCreatedAtFrom(LocalDate createdAtFrom) {
    this.createdAtFrom = createdAtFrom;
  }

  public void setCreatedAtTo(LocalDate createdAtTo) {
    this.createdAtTo = createdAtTo;
  }
}
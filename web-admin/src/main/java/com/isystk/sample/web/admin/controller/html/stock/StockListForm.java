package com.isystk.sample.web.admin.controller.html.stock;

import com.isystk.sample.common.dto.Pageable;
import com.isystk.sample.web.base.controller.html.BaseSearchForm;
import java.math.BigInteger;
import java.time.LocalDate;
import javax.validation.constraints.Digits;
import org.springframework.format.annotation.DateTimeFormat;

public class StockListForm extends BaseSearchForm implements Pageable {

  private static final long serialVersionUID = 7593564324192730932L;

  @Digits(integer = 9, fraction = 0)
  BigInteger stockId;

  String name;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDate createdAtFrom;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  LocalDate createdAtTo;

  public @Digits(integer = 9, fraction = 0) BigInteger getStockId() {
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

  public void setStockId(@Digits(integer = 9, fraction = 0) BigInteger stockId) {
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

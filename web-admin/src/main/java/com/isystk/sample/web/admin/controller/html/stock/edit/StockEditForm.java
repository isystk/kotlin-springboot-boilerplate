package com.isystk.sample.web.admin.controller.html.stock.edit;

import com.isystk.sample.web.base.controller.html.BaseForm;
import java.math.BigInteger;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.NumberFormat;

public class StockEditForm extends BaseForm {

  private static final long serialVersionUID = 7593564324192730932L;

  @NotNull
  BigInteger stockId;

  @NotBlank
  String name;

  @NotBlank
  String detail;

  @NotBlank
  @NumberFormat
  String price;

  @NotBlank
  @NumberFormat
  String quantity;

  @NotBlank
  String stockImageName;

  @NotBlank
  String stockImageData;

  public @NotNull BigInteger getStockId() {
    return this.stockId;
  }

  public @NotBlank String getName() {
    return this.name;
  }

  public @NotBlank String getDetail() {
    return this.detail;
  }

  public @NotBlank String getPrice() {
    return this.price;
  }

  public @NotBlank String getQuantity() {
    return this.quantity;
  }

  public @NotBlank String getStockImageName() {
    return this.stockImageName;
  }

  public @NotBlank String getStockImageData() {
    return this.stockImageData;
  }

  public void setStockId(@NotNull BigInteger stockId) {
    this.stockId = stockId;
  }

  public void setName(@NotBlank String name) {
    this.name = name;
  }

  public void setDetail(@NotBlank String detail) {
    this.detail = detail;
  }

  public void setPrice(@NotBlank String price) {
    this.price = price;
  }

  public void setQuantity(@NotBlank String quantity) {
    this.quantity = quantity;
  }

  public void setStockImageName(@NotBlank String stockImageName) {
    this.stockImageName = stockImageName;
  }

  public void setStockImageData(@NotBlank String stockImageData) {
    this.stockImageData = stockImageData;
  }
}

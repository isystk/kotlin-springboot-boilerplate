package com.isystk.sample.web.admin.controller.html.stock.regist;

import com.isystk.sample.web.base.controller.html.BaseForm;
import javax.validation.constraints.NotBlank;
import org.springframework.format.annotation.NumberFormat;

public class StockRegistForm extends BaseForm {

  private static final long serialVersionUID = 7593564324192730932L;

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

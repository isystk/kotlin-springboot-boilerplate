package com.isystk.sample.web.front.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isystk.sample.common.dto.Dto;
import java.math.BigInteger;
import java.time.LocalDateTime;

/**
 * 商品フロント表示用DTO
 */
public class StockSearchResultDto implements Dto {

  @JsonProperty("id")
  BigInteger stockId;

  String name;

  String detail;

  Integer price;

  String imgpath;

  @JsonProperty("img_url")
  String imgUrl;

  Integer quantity;

  LocalDateTime createdAt;

  String createdAtYYYYMMDD;

  String createdAtMMDD;

  public BigInteger getStockId() {
    return this.stockId;
  }

  public String getName() {
    return this.name;
  }

  public String getDetail() {
    return this.detail;
  }

  public Integer getPrice() {
    return this.price;
  }

  public String getImgpath() {
    return this.imgpath;
  }

  public String getImgUrl() {
    return this.imgUrl;
  }

  public Integer getQuantity() {
    return this.quantity;
  }

  public LocalDateTime getCreatedAt() {
    return this.createdAt;
  }

  public String getCreatedAtYYYYMMDD() {
    return this.createdAtYYYYMMDD;
  }

  public String getCreatedAtMMDD() {
    return this.createdAtMMDD;
  }

  @JsonProperty("id")
  public void setStockId(BigInteger stockId) {
    this.stockId = stockId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDetail(String detail) {
    this.detail = detail;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public void setImgpath(String imgpath) {
    this.imgpath = imgpath;
  }

  @JsonProperty("img_url")
  public void setImgUrl(String imgUrl) {
    this.imgUrl = imgUrl;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public void setCreatedAt(LocalDateTime createdAt) {
    this.createdAt = createdAt;
  }

  public void setCreatedAtYYYYMMDD(String createdAtYYYYMMDD) {
    this.createdAtYYYYMMDD = createdAtYYYYMMDD;
  }

  public void setCreatedAtMMDD(String createdAtMMDD) {
    this.createdAtMMDD = createdAtMMDD;
  }
}

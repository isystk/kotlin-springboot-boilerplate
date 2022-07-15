package com.isystk.sample.batch.jobs.importMst;

import com.isystk.sample.batch.item.ItemPosition;
import javax.validation.constraints.NotEmpty;

public class ImportMstStockDto implements ItemPosition {

  /** 商品名 */
  @NotEmpty
  String name;

  /** 説明文 */
  @NotEmpty
  String detail;

  /** 価格 */
  @NotEmpty
  String price;

  /** 画像ファイル名 */
  @NotEmpty
  String imgpath;

  /** 在庫数 */
  @NotEmpty
  String quantity;

  // 取り込み元ファイル
  String sourceName;

  int position;

  public @NotEmpty String getName() {
    return this.name;
  }

  public @NotEmpty String getDetail() {
    return this.detail;
  }

  public @NotEmpty String getPrice() {
    return this.price;
  }

  public @NotEmpty String getImgpath() {
    return this.imgpath;
  }

  public @NotEmpty String getQuantity() {
    return this.quantity;
  }

  public String getSourceName() {
    return this.sourceName;
  }

  public int getPosition() {
    return this.position;
  }

  public void setName(@NotEmpty String name) {
    this.name = name;
  }

  public void setDetail(@NotEmpty String detail) {
    this.detail = detail;
  }

  public void setPrice(@NotEmpty String price) {
    this.price = price;
  }

  public void setImgpath(@NotEmpty String imgpath) {
    this.imgpath = imgpath;
  }

  public void setQuantity(@NotEmpty String quantity) {
    this.quantity = quantity;
  }

  public void setSourceName(String sourceName) {
    this.sourceName = sourceName;
  }

  public void setPosition(int position) {
    this.position = position;
  }
}

package com.isystk.sample.solr.dto;

import com.isystk.sample.common.dto.Dto;
import java.util.Date;
import org.apache.solr.client.solrj.beans.Field;

public class SolrStock implements Dto {

  @Field("id")
  private String id;

  @Field("stock_id")
  private Integer stockId;

  @Field("name")
  private String name;

  @Field("detail")
  private String detail;

  @Field("price")
  private Integer price;

  @Field("imgpath")
  private String imgpath;

  @Field("quantity")
  private Integer quantity;

  @Field("created_at")
  private Date createdAtDate;

  public SolrStock() {
  }

  public String getId() {
    return this.id;
  }

  public Integer getStockId() {
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

  public Integer getQuantity() {
    return this.quantity;
  }

  public Date getCreatedAtDate() {
    return this.createdAtDate;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setStockId(Integer stockId) {
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

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public void setCreatedAtDate(Date createdAtDate) {
    this.createdAtDate = createdAtDate;
  }

  public boolean equals(final Object o) {
    if (o == this) {
      return true;
    }
    if (!(o instanceof SolrStock)) {
      return false;
    }
    final SolrStock other = (SolrStock) o;
    if (!other.canEqual((Object) this)) {
      return false;
    }
    final Object this$id = this.getId();
    final Object other$id = other.getId();
    if (this$id == null ? other$id != null : !this$id.equals(other$id)) {
      return false;
    }
    final Object this$stockId = this.getStockId();
    final Object other$stockId = other.getStockId();
    if (this$stockId == null ? other$stockId != null : !this$stockId.equals(other$stockId)) {
      return false;
    }
    final Object this$name = this.getName();
    final Object other$name = other.getName();
    if (this$name == null ? other$name != null : !this$name.equals(other$name)) {
      return false;
    }
    final Object this$detail = this.getDetail();
    final Object other$detail = other.getDetail();
    if (this$detail == null ? other$detail != null : !this$detail.equals(other$detail)) {
      return false;
    }
    final Object this$price = this.getPrice();
    final Object other$price = other.getPrice();
    if (this$price == null ? other$price != null : !this$price.equals(other$price)) {
      return false;
    }
    final Object this$imgpath = this.getImgpath();
    final Object other$imgpath = other.getImgpath();
    if (this$imgpath == null ? other$imgpath != null : !this$imgpath.equals(other$imgpath)) {
      return false;
    }
    final Object this$quantity = this.getQuantity();
    final Object other$quantity = other.getQuantity();
    if (this$quantity == null ? other$quantity != null : !this$quantity.equals(other$quantity)) {
      return false;
    }
    final Object this$createdAtDate = this.getCreatedAtDate();
    final Object other$createdAtDate = other.getCreatedAtDate();
    if (this$createdAtDate == null ? other$createdAtDate != null
        : !this$createdAtDate.equals(other$createdAtDate)) {
      return false;
    }
    return true;
  }

  protected boolean canEqual(final Object other) {
    return other instanceof SolrStock;
  }

  public int hashCode() {
    final int PRIME = 59;
    int result = 1;
    final Object $id = this.getId();
    result = result * PRIME + ($id == null ? 43 : $id.hashCode());
    final Object $stockId = this.getStockId();
    result = result * PRIME + ($stockId == null ? 43 : $stockId.hashCode());
    final Object $name = this.getName();
    result = result * PRIME + ($name == null ? 43 : $name.hashCode());
    final Object $detail = this.getDetail();
    result = result * PRIME + ($detail == null ? 43 : $detail.hashCode());
    final Object $price = this.getPrice();
    result = result * PRIME + ($price == null ? 43 : $price.hashCode());
    final Object $imgpath = this.getImgpath();
    result = result * PRIME + ($imgpath == null ? 43 : $imgpath.hashCode());
    final Object $quantity = this.getQuantity();
    result = result * PRIME + ($quantity == null ? 43 : $quantity.hashCode());
    final Object $createdAtDate = this.getCreatedAtDate();
    result = result * PRIME + ($createdAtDate == null ? 43 : $createdAtDate.hashCode());
    return result;
  }

  public String toString() {
    return "SolrStock(id=" + this.getId() + ", stockId=" + this.getStockId() + ", name="
        + this.getName() + ", detail=" + this.getDetail() + ", price=" + this.getPrice()
        + ", imgpath=" + this.getImgpath() + ", quantity=" + this.getQuantity() + ", createdAtDate="
        + this.getCreatedAtDate() + ")";
  }
}
package com.isystk.sample.web.front.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.isystk.sample.common.dto.Dto;
import com.isystk.sample.domain.dto.CartRepositoryDto;
import java.util.List;

/**
 * マイカート表示用DTO
 */
public class CartSearchResultDto implements Dto {

  @JsonProperty("data")
  List<CartRepositoryDto> cartList;

  String username;

  Integer count;

  Integer sum;

  public List<CartRepositoryDto> getCartList() {
    return this.cartList;
  }

  public String getUsername() {
    return this.username;
  }

  public Integer getCount() {
    return this.count;
  }

  public Integer getSum() {
    return this.sum;
  }

  @JsonProperty("data")
  public void setCartList(List<CartRepositoryDto> cartList) {
    this.cartList = cartList;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setCount(Integer count) {
    this.count = count;
  }

  public void setSum(Integer sum) {
    this.sum = sum;
  }
}

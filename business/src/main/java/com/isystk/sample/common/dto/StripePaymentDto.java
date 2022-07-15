package com.isystk.sample.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Stripe用の支払情報DTO
 */
public class StripePaymentDto implements Dto {

  @JsonProperty("client_secret")
  String clientSecret;

  public String getClientSecret() {
    return this.clientSecret;
  }

  @JsonProperty("client_secret")
  public void setClientSecret(String clientSecret) {
    this.clientSecret = clientSecret;
  }
}

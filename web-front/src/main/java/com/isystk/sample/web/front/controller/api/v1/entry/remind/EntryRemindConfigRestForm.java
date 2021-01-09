package com.isystk.sample.web.front.controller.api.v1.entry.remind;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

class EntryRemindConfigRestForm implements Serializable {

  private static final long serialVersionUID = 7593564324192730932L;

  /**
   * パスワード
   */
  @NotBlank
  @Size(max = 100)
  String password;

  /**
   * パスワード(確認用)
   */
  @NotBlank
  @Size(max = 100)
  String passwordConf;

  @NotBlank
  String onetimeKey;

  public @NotBlank @Size(max = 100) String getPassword() {
    return this.password;
  }

  public @NotBlank @Size(max = 100) String getPasswordConf() {
    return this.passwordConf;
  }

  public @NotBlank String getOnetimeKey() {
    return this.onetimeKey;
  }

  public void setPassword(@NotBlank @Size(max = 100) String password) {
    this.password = password;
  }

  public void setPasswordConf(@NotBlank @Size(max = 100) String passwordConf) {
    this.passwordConf = passwordConf;
  }

  public void setOnetimeKey(@NotBlank String onetimeKey) {
    this.onetimeKey = onetimeKey;
  }
}

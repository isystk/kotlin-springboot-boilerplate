package com.isystk.sample.web.front.controller.html.password.config;

import java.io.Serializable;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

class PasswordResetConfigForm implements Serializable {

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
  String passwordConfirmation;

  public @NotBlank @Size(max = 100) String getPassword() {
    return this.password;
  }

  public @NotBlank @Size(max = 100) String getPasswordConfirmation() {
    return this.passwordConfirmation;
  }

  public void setPassword(@NotBlank @Size(max = 100) String password) {
    this.password = password;
  }

  public void setPasswordConfirmation(@NotBlank @Size(max = 100) String passwordConfirmation) {
    this.passwordConfirmation = passwordConfirmation;
  }
}

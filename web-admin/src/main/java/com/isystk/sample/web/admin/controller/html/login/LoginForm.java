package com.isystk.sample.web.admin.controller.html.login;

import java.io.Serializable;
import javax.validation.constraints.NotEmpty;

public class LoginForm implements Serializable {

  private static final long serialVersionUID = 7593564324192730932L;

  @NotEmpty
  String loginId;

  @NotEmpty
  String password;

  public @NotEmpty String getLoginId() {
    return this.loginId;
  }

  public @NotEmpty String getPassword() {
    return this.password;
  }

  public void setLoginId(@NotEmpty String loginId) {
    this.loginId = loginId;
  }

  public void setPassword(@NotEmpty String password) {
    this.password = password;
  }
}

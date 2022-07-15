package com.isystk.sample.web.front.controller.html.register;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class RegisterForm implements Serializable {

  private static final long serialVersionUID = 7593564324192730932L;
  @NotEmpty
  String name;

  @NotEmpty
  @Email
  String email;

  @NotEmpty
  String password;

  @NotEmpty
  String passwordConfirmation;

  public @NotEmpty String getName() {
    return this.name;
  }

  public @NotEmpty @Email String getEmail() {
    return this.email;
  }

  public @NotEmpty String getPassword() {
    return this.password;
  }

  public @NotEmpty String getPasswordConfirmation() {
    return this.passwordConfirmation;
  }

  public void setName(@NotEmpty String name) {
    this.name = name;
  }

  public void setEmail(@NotEmpty @Email String email) {
    this.email = email;
  }

  public void setPassword(@NotEmpty String password) {
    this.password = password;
  }

  public void setPasswordConfirmation(@NotEmpty String passwordConfirmation) {
    this.passwordConfirmation = passwordConfirmation;
  }
}

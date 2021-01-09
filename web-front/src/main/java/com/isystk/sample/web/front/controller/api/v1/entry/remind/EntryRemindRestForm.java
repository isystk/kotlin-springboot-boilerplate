package com.isystk.sample.web.front.controller.api.v1.entry.remind;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

class EntryRemindRestForm implements Serializable {

  private static final long serialVersionUID = 7593564324192730932L;

  /**
   * メールアドレス
   */
  @NotBlank
  @Email
  @Size(max = 100)
  String email;

  public @NotBlank @Email @Size(max = 100) String getEmail() {
    return this.email;
  }

  public void setEmail(@NotBlank @Email @Size(max = 100) String email) {
    this.email = email;
  }
}
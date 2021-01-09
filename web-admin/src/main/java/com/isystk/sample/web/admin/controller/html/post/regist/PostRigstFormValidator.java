package com.isystk.sample.web.admin.controller.html.post.regist;

import com.isystk.sample.common.validator.AbstractValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

/**
 * 投稿 入力チェック
 */
@Component
public class PostRigstFormValidator extends AbstractValidator<PostRegistForm> {

  @Override
  protected void doValidate(PostRegistForm form, Errors errors) {

  }
}

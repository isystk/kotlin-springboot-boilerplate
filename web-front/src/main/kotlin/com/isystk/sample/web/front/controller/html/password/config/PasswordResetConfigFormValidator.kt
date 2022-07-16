package com.isystk.sample.web.front.controller.html.password.config

import com.isystk.sample.common.util.ValidateUtils
import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * 会員パスワード変更 入力チェック
 */
@Component
class PasswordResetConfigFormValidator : AbstractValidator<PasswordResetConfigForm?>() {
    override fun doValidate(
            form: PasswordResetConfigForm?, errors: Errors?) {
        // 確認用パスワードと突き合わせる
        if (form != null) {
            if (ValidateUtils.isNotEquals(form.password, form.passwordConfirmation)) {
                errors?.rejectValue("password", "errros.unmatchPassword")
                errors?.rejectValue("passwordConf", "errros.unmatchPassword")
            }
        }
    }
}
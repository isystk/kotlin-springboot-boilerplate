package com.isystk.sample.web.front.controller.html.password

import com.isystk.sample.common.validator.AbstractValidator
import com.isystk.sample.domain.dao.UserDao
import com.isystk.sample.domain.dto.UserCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * 会員パスワード変更 入力チェック
 */
@Component
class PasswordResetFormValidator : AbstractValidator<PasswordResetForm?>() {
    @Autowired
    var userDao: UserDao? = null
    override fun doValidate(
            form: PasswordResetForm?, errors: Errors?) {
        // メールアドレスの存在チェック
        val criteria = UserCriteria()
        if (form != null) {
            criteria.emailEq = form.email
        }
        if (userDao!!.findOne(criteria).orElse(null) == null) {
            if (errors != null) {
                errors.rejectValue("email", "errros.emailNotExist")
            }
        }
    }
}
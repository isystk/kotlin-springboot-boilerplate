package com.isystk.sample.web.front.controller.html.register

import com.isystk.sample.common.util.ValidateUtils
import com.isystk.sample.common.validator.AbstractValidator
import com.isystk.sample.domain.dao.UserDao
import com.isystk.sample.domain.dto.UserCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * 会員登録 入力チェック
 */
@Component
class RegisterFormValidator : AbstractValidator<RegisterForm?>() {
    @Autowired
    var userDao: UserDao? = null
    override fun doValidate(form: RegisterForm?, errors: Errors?) {
        // 確認用パスワードと突き合わせる
        if (form != null) {
            if (ValidateUtils.isNotEquals(form.password, form.passwordConfirmation)) {
                errors?.rejectValue("password", "errros.unmatchPassword")
                errors?.rejectValue("password_confirmation", "errros.unmatchPassword")
            }
        }

        // メールアドレスの存在チェック
        val criteria = UserCriteria()
        if (form != null) {
            criteria.emailEq = form.email
        }
        if (userDao!!.findOne(criteria).orElse(null) != null) {
            errors?.rejectValue("email", "errros.emailExist")
        }
    }
}
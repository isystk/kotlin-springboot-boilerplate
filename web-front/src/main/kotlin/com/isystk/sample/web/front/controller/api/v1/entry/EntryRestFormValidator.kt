package com.isystk.sample.web.front.controller.api.v1.entry

import com.isystk.sample.common.util.ValidateUtils
import com.isystk.sample.common.validator.AbstractValidator
import com.isystk.sample.domain.dao.TUserDao
import com.isystk.sample.domain.dto.TUserCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * 会員登録 入力チェック
 */
@Component
class EntryRestFormValidator : AbstractValidator<EntryRestForm?>() {
    @Autowired
    var tUserDao: TUserDao? = null

    override fun doValidate(form: EntryRestForm?, errors: Errors?) {
        // 確認用パスワードと突き合わせる
        if (ValidateUtils.isNotEquals(form!!.password, form!!.passwordConf)) {
            errors!!.rejectValue("password", "errros.unmatchPassword")
            errors!!.rejectValue("passwordConf", "errros.unmatchPassword")
        }

        // メールアドレスの存在チェック
        val criteria = TUserCriteria()
        criteria.emailEq = form!!.email
        if (tUserDao!!.findOne(criteria).orElse(null) != null) {
            errors!!.rejectValue("email", "errros.emailExist")
        }
    }
}
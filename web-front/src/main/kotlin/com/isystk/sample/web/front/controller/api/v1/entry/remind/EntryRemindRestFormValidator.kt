package com.isystk.sample.web.front.controller.api.v1.entry.remind

import com.isystk.sample.common.validator.AbstractValidator
import com.isystk.sample.domain.dao.TUserDao
import com.isystk.sample.domain.dto.TUserCriteria
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * 会員パスワード変更 入力チェック
 */
@Component
class EntryRemindRestFormValidator : AbstractValidator<EntryRemindRestForm?>() {
    @Autowired
    var tUserDao: TUserDao? = null

    override fun doValidate(form: EntryRemindRestForm?, errors: Errors?) {
        // メールアドレスの存在チェック
        val criteria = TUserCriteria()
        criteria.emailEq = form!!.email
        if (tUserDao!!.findOne(criteria).orElse(null) == null) {
            errors!!.rejectValue("email", "errros.emailNotExist")
        }
    }
}
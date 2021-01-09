package com.isystk.sample.web.front.controller.api.v1.entry.remind

import com.isystk.sample.common.util.ValidateUtils
import com.isystk.sample.common.validator.AbstractValidator
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

/**
 * 会員パスワード変更 入力チェック
 */
@Component
class EntryRemindConfigRestFormValidator : AbstractValidator<EntryRemindConfigRestForm?>() {

    override fun doValidate(form: EntryRemindConfigRestForm?, errors: Errors?) {
        // 確認用パスワードと突き合わせる
        if (ValidateUtils.isNotEquals(form!!.password, form.passwordConf)) {
            errors!!.rejectValue("password", "errros.unmatchPassword")
            errors!!.rejectValue("passwordConf", "errros.unmatchPassword")
        }
    }
}
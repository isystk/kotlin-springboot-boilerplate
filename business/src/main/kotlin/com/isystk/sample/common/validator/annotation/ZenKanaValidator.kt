package com.isystk.sample.common.validator.annotation

import org.apache.commons.lang3.StringUtils
import java.util.regex.Pattern
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * 入力チェック（全角カナ）
 */
class ZenKanaValidator : ConstraintValidator<ZenKana?, String?> {
    override fun initialize(ZenKana: ZenKana?) {}
    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        var isValid = false
        if (StringUtils.isEmpty(value)) {
            isValid = true
        } else {
            val m = p.matcher(value)
            if (m.matches()) {
                isValid = true
            }
        }
        return isValid
    }

    companion object {
        val p = Pattern.compile("^[ァ-ヶ]+$")
    }
}
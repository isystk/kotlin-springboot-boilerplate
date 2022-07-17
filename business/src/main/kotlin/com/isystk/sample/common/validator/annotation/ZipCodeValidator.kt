package com.isystk.sample.common.validator.annotation

import org.apache.commons.lang3.StringUtils
import java.util.regex.Pattern
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * 入力チェック（郵便番号）
 */
class ZipCodeValidator : ConstraintValidator<ZipCode?, String?> {
    override fun initialize(ZipCode: ZipCode?) {}
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
        val p = Pattern.compile("^[0-9]{3}-[0-9]{4}$")
    }
}
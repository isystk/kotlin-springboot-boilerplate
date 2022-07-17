package com.isystk.sample.common.validator.annotation

import com.isystk.sample.common.util.ValidateUtils
import org.slf4j.LoggerFactory
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * 入力チェック（電話番号）
 */
class PhoneNumberValidator : ConstraintValidator<PhoneNumber, String?> {
    private var pattern: Pattern? = null
    override fun initialize(phoneNumber: PhoneNumber) {
        pattern = try {
            Pattern.compile(phoneNumber.regexp)
        } catch (e: PatternSyntaxException) {
            log.error("invalid regular expression.", e)
            throw e
        }
    }

    override fun isValid(value: String?, context: ConstraintValidatorContext): Boolean {
        var isValid = false
        if (ValidateUtils.isEmpty(value)) {
            isValid = true
        } else {
            val m = pattern!!.matcher(value)
            if (m.matches()) {
                isValid = true
            }
        }
        return isValid
    }

    companion object {
        private val log = LoggerFactory.getLogger(PhoneNumberValidator::class.java)
    }
}
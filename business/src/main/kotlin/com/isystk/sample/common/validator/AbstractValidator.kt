package com.isystk.sample.common.validator

import com.isystk.sample.common.logger
import org.springframework.validation.Errors
import org.springframework.validation.Validator

/**
 * 基底入力チェッククラス
 */
abstract class AbstractValidator<T> : Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return true
    }

    override fun validate(target: Any, errors: Errors) {
        try {
            val hasErrors = errors.hasErrors()
            if (!hasErrors || passThruBeanValidation(hasErrors)) {
                // 各機能で実装しているバリデーションを実行する
                doValidate(target as T, errors)
            }
        } catch (e: RuntimeException) {
            logger.error("validate error", e)
            throw e
        }
    }

    /**
     * 入力チェックを実施します。
     *
     * @param form
     * @param errors
     */
    protected abstract fun doValidate(form: T, errors: Errors?)

    /**
     * 相関チェックバリデーションを実施するかどうかを示す値を返します。<br></br> デフォルトは、JSR-303バリデーションでエラーがあった場合は相関チェックを実施しません。
     *
     * @return
     */
    protected fun passThruBeanValidation(hasErrors: Boolean): Boolean {
        return false
    }

}
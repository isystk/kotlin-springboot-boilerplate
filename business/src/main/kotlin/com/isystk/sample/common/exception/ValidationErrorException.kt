package com.isystk.sample.common.exception

import org.springframework.validation.Errors
import java.util.*

/**
 * バリデーションエラー
 */
class ValidationErrorException(errors: Errors?) : RuntimeException() {
    val errors: Optional<Errors>

    init {
        this.errors = Optional.ofNullable(errors)
    }

    companion object {
        private const val serialVersionUID = 5084588189148251787L
    }
}
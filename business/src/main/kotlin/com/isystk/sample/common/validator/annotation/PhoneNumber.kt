package com.isystk.sample.common.validator.annotation

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * 入力チェック（電話番号）
 */
@Documented
@Constraint(validatedBy = [PhoneNumberValidator::class])
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FIELD)
@Retention(RetentionPolicy.RUNTIME)
annotation class PhoneNumber(val regexp: String = "^[0-9]{1,4}-[0-9]{1,4}-[0-9]{4}$", val message: String = "{validator.annotation.PhoneNumber.message}", val groups: Array<KClass<*>> = [], val payload: Array<KClass<out Payload>> = []) {
    @Target(AnnotationTarget.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    annotation class List(vararg val value: PhoneNumber)
}
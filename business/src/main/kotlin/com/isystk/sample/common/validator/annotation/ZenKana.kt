package com.isystk.sample.common.validator.annotation

import java.lang.annotation.Documented
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

/**
 * 入力チェック（全角カナ）
 */
@Documented
@Constraint(validatedBy = [ZenKanaValidator::class])
@Target(AnnotationTarget.FUNCTION, AnnotationTarget.PROPERTY_GETTER, AnnotationTarget.PROPERTY_SETTER, AnnotationTarget.FIELD)
@Retention(RetentionPolicy.RUNTIME)
annotation class ZenKana(val message: String = "{validator.annotation.ZenKana.message}", val groups: Array<KClass<*>> = [], val payload: Array<KClass<out Payload>> = []) {
    @Target(AnnotationTarget.FIELD)
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    annotation class List(vararg val value: ZenKana)
}
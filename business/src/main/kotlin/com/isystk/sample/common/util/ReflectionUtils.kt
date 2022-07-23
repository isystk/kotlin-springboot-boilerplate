package com.isystk.sample.common.util

import java.lang.reflect.Field
import java.util.*
import java.util.stream.Stream

/**
 * リフレクション関連ユーティリティ
 */
object ReflectionUtils {

    /**
     * 指定したアノテーションが付与されているフィールドを返します。
     *
     * @param clazz
     * @param annotationType
     * @param <A>
     * @return
    </A> */
    fun <A : Annotation?> findWithAnnotation(clazz: Class<*>,
                                             annotationType: Class<A>?): Stream<Field> {
        return Optional.ofNullable(clazz.declaredFields).map<Stream<Field>> { array: Array<Field?>? -> Arrays.stream(array) }
                .orElseGet { Stream.empty() }
                .filter { f: Field -> f.isAnnotationPresent(annotationType) }
    }

    /**
     * 指定したフィールドの値を返します。
     *
     * @param f
     * @param obj
     * @return
     */
    fun getFieldValue(f: Field, obj: Any?): Any? {
        try {
            f.isAccessible = true
            return f[obj]
        } catch (e: Exception) {
            // ignore
        }
        return null
    }
}
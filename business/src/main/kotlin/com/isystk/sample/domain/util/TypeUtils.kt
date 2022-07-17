package com.isystk.sample.domain.util

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

/**
 * Typeを扱うユーティリティ
 */
object TypeUtils {
    /**
     * Typeを返します。
     *
     * @param clazz
     * @param <T>
     * @return
    </T> */
    fun <T> toListType(clazz: Class<T>): Type {
        return ListParameterizedType(clazz)
    }

    private class ListParameterizedType(private val type: Type) : ParameterizedType {
        override fun getActualTypeArguments(): Array<Type> {
            return arrayOf(type)
        }

        override fun getRawType(): Type {
            return ArrayList::class.java
        }

        override fun getOwnerType(): Type? {
            return null
        }
    }
}
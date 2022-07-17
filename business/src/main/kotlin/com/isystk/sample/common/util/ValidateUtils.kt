package com.isystk.sample.common.util

import org.apache.commons.lang3.StringUtils

/**
 * 入力チェックユーティリティ
 */
object ValidateUtils {
    /**
     * 引数の値の真偽を示す値を返します。
     *
     * @param value
     * @return
     */
    @JvmStatic
    fun isTrue(value: Boolean?): Boolean {
        return value != null && value
    }

    /**
     * 値の真偽を示す値を返します。
     *
     * @param value
     * @return
     */
    @JvmStatic
    fun isFalse(value: Boolean?): Boolean {
        return !isTrue(value)
    }

    /**
     * 値の存在可否をチェックします。
     *
     * @param value
     * @return
     */
    @JvmStatic
    fun isEmpty(value: String?): Boolean {
        return value == null || value.length == 0
    }

    /**
     * コレクションの存在可否をチェックします。
     *
     * @param collection
     * @return
     */
    @JvmStatic
    fun isEmpty(collection: Collection<*>?): Boolean {
        return collection == null || collection.isEmpty()
    }

    /**
     * 配列の存在可否をチェックします。
     *
     * @param array
     * @return
     */
    @JvmStatic
    fun isEmpty(array: Array<Any?>?): Boolean {
        return array == null || array.size == 0
    }

    /**
     * マップの存在可否をチェックします。
     *
     * @param map
     * @return
     */
    @JvmStatic
    fun isEmpty(map: Map<*, *>?): Boolean {
        return map == null || map.isEmpty()
    }

    /**
     * 存在可否をチェックします。
     *
     * @param value
     * @return
     */
    @JvmStatic
    fun isNotEmpty(value: String?): Boolean {
        return !isEmpty(value)
    }

    /**
     * 存在可否をチェックします。
     *
     * @param collection
     * @return
     */
    @JvmStatic
    fun isNotEmpty(collection: Collection<*>?): Boolean {
        return !isEmpty(collection)
    }

    /**
     * 存在可否をチェックします。
     *
     * @param array
     * @return
     */
    @JvmStatic
    fun isNotEmpty(array: Array<Any?>?): Boolean {
        return !isEmpty(array)
    }

    /**
     * 存在可否をチェックします。
     *
     * @param map
     * @return
     */
    @JvmStatic
    fun isNotEmpty(map: Map<*, *>?): Boolean {
        return !isEmpty(map)
    }

    /**
     * 文字列と正規表現のマッチ可否をチェックします。
     *
     * @param value
     * @param regex
     * @return
     */
    @JvmStatic
    fun matches(value: String, regex: Regex): Boolean {
        return isNotEmpty(value) && value.matches(regex)
    }

    /**
     * 値が数字のみかチェックします。
     *
     * @param value
     * @return
     */
    @JvmStatic
    fun isNumeric(value: String?): Boolean {
        return StringUtils.isNumeric(value)
    }

    /**
     * 値がASCII文字のみかチェックします。
     *
     * @param value
     * @return
     */
    @JvmStatic
    fun isAscii(value: String?): Boolean {
        return StringUtils.isAsciiPrintable(value)
    }

    /**
     * 引数同士が等しいかチェックします。
     *
     * @param obj1
     * @param obj2
     * @return
     */
    @JvmStatic
    fun isEquals(obj1: Any?, obj2: Any?): Boolean {
        return obj1 == obj2
    }

    /**
     * 引数同士が等しいかチェックします。
     *
     * @param obj1
     * @param obj2
     * @return
     */
    @JvmStatic
    fun isNotEquals(obj1: Any?, obj2: Any?): Boolean {
        return !isEquals(obj1, obj2)
    }
}
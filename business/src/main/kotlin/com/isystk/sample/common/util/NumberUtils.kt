/*
 * NumberUtil.java 2011/03/28 iseyoshitaka
 */
package com.isystk.sample.common.util

import java.math.BigDecimal
import java.math.BigInteger

/**
 *
 */
object NumberUtils {
    /**
     * 文字列をIntegerに変換します
     *
     * @param str          文字列
     * @param defaultValue 数値変換に失敗した場合のデフォルト値
     * @return Integer
     */
    /**
     * 文字列をIntegerに変換します
     *
     * @param str 文字列
     * @return Integer
     */
    @JvmStatic
    @JvmOverloads
    fun toInteger(str: String?, defaultValue: Int? = null): Int? {
        return if (str == null) {
            defaultValue
        } else try {
            str.toInt()
        } catch (nfe: NumberFormatException) {
            defaultValue
        }
    }

    /**
     * 文字列の配列をIntegerの配列に変換します (null の場合は値が消えてしまうので非推奨)
     *
     * @param strArray 文字列の配列
     * @return Integerの配列
     */
    @JvmStatic
    fun toIntegerArray(strArray: Array<String?>?): Array<Int>? {
        if (strArray == null) {
            return null
        }
        val intArray: MutableList<Int> = ArrayList()
        for (i in strArray.indices) {
            val num = toInteger(strArray[i], null)
            if (num != null) {
                intArray.add(num)
            }
        }
        return intArray.toTypedArray()
    }

    /**
     * 文字列の配列をIntegerの配列に変換します。null の場合も含みます。
     *
     * @param strArray 文字列の配列
     * @return Integerの配列
     */
    @JvmStatic
    fun toIntegerArrayIncludeNull(strArray: Array<String?>?): Array<Int?>? {
        if (strArray == null) {
            return null
        }
        val intArray: MutableList<Int?> = ArrayList()
        for (i in strArray.indices) {
            val num = toInteger(strArray[i], null)
            intArray.add(num)
        }
        return intArray.toTypedArray()
    }

    /**
     * 文字列をIntegerに変換します
     *
     * @param strArray     文字列の配列
     * @param defaultValue 数値変換に失敗した場合のデフォルト値
     * @return Integer
     */
    @JvmStatic
    fun toIntegerArray(strArray: Array<String?>?, defaultValue: Array<Int>?): Array<Int>? {
        return if (strArray == null) {
            defaultValue
        } else toIntegerArray(strArray)
    }

    /**
     * 値がNULLの場合にデフォルト値を設定します。
     */
    @JvmStatic
    fun toInteger(num: Int?, defaultValue: Int): Int {
        return num ?: defaultValue
    }

    /**
     * 値がNULLの場合にデフォルト値を設定します。
     */
    @JvmStatic
    fun toBigInteger(num: BigInteger?, defaultValue: BigInteger): BigInteger {
        return num ?: defaultValue
    }
    /**
     * 文字列をBigIntegerに変換します
     *
     * @param str          文字列
     * @param defaultValue 数値変換に失敗した場合のデフォルト値
     * @return BigInteger
     */
    /**
     * 文字列をBigIntegerに変換します
     *
     * @param str 文字列
     * @return BigInteger
     * @see NumberUtils.toBigInteger
     */
    @JvmStatic
    @JvmOverloads
    fun toBigInteger(str: String?, defaultValue: BigInteger? = null): BigInteger? {
        return if (str == null) {
            defaultValue
        } else try {
            BigInteger(str)
        } catch (nfe: NumberFormatException) {
            defaultValue
        }
    }

    /**
     * 文字列の配列をBigIntegerの配列に変換します
     *
     * @param strArray 文字列の配列
     * @return BigIntegerの配列
     */
    @JvmStatic
    fun toBigIntegerArray(strArray: Array<String?>): Array<BigInteger?> {
        val intArray = arrayOfNulls<BigInteger>(strArray.size)
        for (i in strArray.indices) {
            intArray[i] = toBigInteger(strArray[i], null)
        }
        return intArray
    }
    /**
     * IntegerをBigIntegerに変換します
     *
     * @param num          Integer値
     * @param defaultValue 数値変換に失敗した場合のデフォルト値
     * @return BigInteger
     */
    /**
     * IntegerをBigIntegerに変換します
     *
     * @param num Integer値
     * @return BigInteger
     * @see NumberUtils.toBigInteger
     */
    @JvmStatic
    @JvmOverloads
    fun toBigInteger(num: Int?, defaultValue: BigInteger? = null): BigInteger? {
        return if (num == null) {
            defaultValue
        } else try {
            BigInteger(num.toString())
        } catch (nfe: NumberFormatException) {
            defaultValue
        }
    }
    /**
     * 文字列をDoubleに変換します
     *
     * @param str          文字列
     * @param defaultValue 数値変換に失敗した場合のデフォルト値
     * @return Double
     */
    /**
     * 文字列をDoubleに変換します
     *
     * @param str 文字列
     * @return Double
     * @see NumberUtils.toDouble
     */
    @JvmStatic
    @JvmOverloads
    fun toDouble(str: String?, defaultValue: Double? = null): Double? {
        return if (str == null) {
            defaultValue
        } else try {
            str.toDouble()
        } catch (nfe: NumberFormatException) {
            defaultValue
        }
    }
    /**
     * 文字列をDoubleに変換します
     *
     * @param str          文字列
     * @param defaultValue 数値変換に失敗した場合のデフォルト値
     * @return Double
     */
    /**
     * 文字列をBigDecimalに変換します
     *
     * @param str 文字列
     * @return BigDecimal
     * @see NumberUtils.toBigDecimal
     */
    @JvmStatic
    @JvmOverloads
    fun toBigDecimal(str: String?, defaultValue: BigDecimal? = null): BigDecimal? {
        return if (str == null) {
            defaultValue
        } else try {
            BigDecimal(str)
        } catch (nfe: NumberFormatException) {
            defaultValue
        }
    }

    /**
     * 1~999999999の範囲内で乱数を取得します。
     *
     * @return
     */
    @JvmStatic
    val randomInteger: Int
        get() = Integer.valueOf((Math.random() * 999999999 + 1).toInt())

    /**
     * 対象がNULLの場合に指定したデフォルト値を返却します。
     *
     * @return
     */
    @JvmStatic
    fun defaultValue(target: Int?, defaultValue: Int): Int {
        return target ?: defaultValue
    }

    /**
     * 対象がNULLの場合に指定したデフォルト値を返却します。
     *
     * @return
     */
    @JvmStatic
    fun defaultValue(target: BigInteger?, defaultValue: BigInteger): BigInteger {
        return target ?: defaultValue
    }

    /**
     * 引数で指定したリストの和を返却します。
     *
     * @return
     */
    @JvmStatic
    fun sum(targets: List<Any>?): Double {
        if (targets == null) {
            return java.lang.Double.valueOf(0.0)
        }
        var sum = 0.0
        for (i in targets.indices) {
            val target = java.lang.Double.valueOf(targets[i].toString())
            sum = sum + target
        }
        return sum
    }

    /**
     * 引数で指定したリストの平均を返却します。
     *
     * @return
     */
    @JvmStatic
    fun avg(targets: List<Any>?): Double {
        if (targets == null) {
            return java.lang.Double.valueOf(0.0)
        }
        var sum = 0.0
        for (i in targets.indices) {
            val target = java.lang.Double.valueOf(targets[i].toString())
            sum = sum + target
        }
        return sum / targets.size
    }
}
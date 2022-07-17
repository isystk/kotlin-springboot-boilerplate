package com.isystk.sample.common.util

import org.apache.commons.compress.utils.Lists

/**
 * 文字列に関するユーティリティ。
 */
object StringUtils {
    /**
     * スペース文字のみで構成されているかチェックします.
     *
     * @param str 文字列
     * @return 空かスペース構成文字のみのときtrue
     */
    @JvmStatic
    fun isBlankOrSpace(str: String?): Boolean {
        return if (str == null) {
            true
        } else isNullOrEmpty(str.replace("[\t 　]".toRegex(), ""))
    }

    /**
     * 指定した文字列がnullの場合は空文字に変換する。
     *
     * @param string 対象文字列
     * @return 文字列
     */
    @JvmStatic
    fun nullToEmpty(string: Any?): String {
        return string?.toString() ?: ""
    }

    /**
     * 指定した文字列がnullまたは空文字列であるか否かを取得する。
     *
     * @param string 対象文字列
     * @return 文字列がnullまたは空文字列の場合true.そうでなければfalse.
     */
    @JvmStatic
    fun isNullOrEmpty(string: Any?): Boolean {
        return string == null || string.toString().length == 0
    }

    /**
     * すべての値が、スペース文字のみで構成されているかチェックします.
     *
     * @param str 文字列
     * @return すべての値が、空かスペース構成文字のみのときtrue
     */
    @JvmStatic
    fun isAllBlankOrSpace(str: Array<String?>?): Boolean {
        if (str == null) {
            return true
        }
        var result = true
        for (a in str) {
            if (!isBlankOrSpace(a)) {
                result = false
                break
            }
        }
        return result
    }

    /**
     * カンマ区切りの文字列をリストに変換する
     * @param line
     * @return
     */
    @JvmStatic
    fun csvSplit(line: String): List<String> {
        val list: MutableList<String> = Lists.newArrayList()
        if (isBlankOrSpace(line)) {
            return list
        }
        val words = line.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        for (word in words) {
            list.add(trimDoubleQuot(word))
        }
        return list
    }

    /**
     * 文字列前後のダブルクォーテーションを削除するFunction
     * @param str 文字列
     * @return 前後のダブルクォーテーションを削除した文字列
     */
    @JvmStatic
    fun trimDoubleQuot(str: String): String {
        var str = str
        if (isBlankOrSpace(str)) {
            return str
        }
        str = str.trim { it <= ' ' }
        val c = '"'
        return if (str[0] == c && str[str.length - 1] == c) {
            str.substring(1, str.length - 1)
        } else {
            str
        }
    }

    /**
     * 文字を連結する
     *
     * @param separator 分割文字列
     * @param objects Stringの集合
     * @return 連結後の文字列
     */
    @JvmStatic
    fun join(separator: String?, vararg objects: Any?): String {
        val sb = StringBuilder()
        var first = true
        for (`object` in objects) {
            val str = nullToEmpty(`object`)
            if (str.length != 0) {
                if (!first) {
                    sb.append(separator)
                }
                sb.append(str)
                first = false
            }
        }
        return sb.toString()
    }
}
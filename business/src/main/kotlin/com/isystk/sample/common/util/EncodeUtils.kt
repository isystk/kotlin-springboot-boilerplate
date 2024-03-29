package com.isystk.sample.common.util

import java.io.UnsupportedEncodingException
import java.net.URLEncoder

/**
 * エンコードユーティリティ
 */
object EncodeUtils {

    /**
     * UTF-8でエンコードした文字列を返します。
     *
     * @param filename
     * @return
     */
    fun encodeUtf8(filename: String): String? {
        var encoded: String? = null
        try {
            encoded = URLEncoder.encode(filename, "UTF-8")
        } catch (ignore: UnsupportedEncodingException) {
            // should never happens
        }
        return encoded
    }
}
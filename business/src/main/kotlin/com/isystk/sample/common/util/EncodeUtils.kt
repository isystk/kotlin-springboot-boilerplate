package com.isystk.sample.common.util

import org.slf4j.LoggerFactory
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

/**
 * エンコードユーティリティ
 */
object EncodeUtils {
    private val log = LoggerFactory.getLogger(EncodeUtils::class.java)

    /**
     * UTF-8でエンコードした文字列を返します。
     *
     * @param filename
     * @return
     */
    @JvmStatic
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
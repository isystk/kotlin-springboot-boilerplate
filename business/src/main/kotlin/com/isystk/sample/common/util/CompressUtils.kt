package com.isystk.sample.common.util

import com.isystk.sample.common.logger
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorInputStream
import org.apache.commons.compress.compressors.bzip2.BZip2CompressorOutputStream
import org.apache.commons.compress.utils.IOUtils
import java.io.ByteArrayInputStream
import java.io.ByteArrayOutputStream
import java.io.IOException

/**
 * 圧縮ユーティリティ
 */
object CompressUtils {

    /**
     * 入力したバイト配列をBZip2で圧縮して返します。
     *
     * @param input
     * @return
     */
    fun compress(input: ByteArray?): ByteArray {
        var ref: ByteArrayOutputStream? = null
        try {
            ByteArrayInputStream(input).use { bais ->
                ByteArrayOutputStream(input!!.size).use { baos ->
                    BZip2CompressorOutputStream(baos).use { bzip2cos ->
                        IOUtils.copy(bais, bzip2cos)
                        ref = baos
                    }
                }
            }
        } catch (e: IOException) {
            logger.error("failed to encode.", e)
            throw RuntimeException(e)
        }
        return ref!!.toByteArray()
    }

    /**
     * 入力したバイト配列をBZip2で展開して返します。
     *
     * @param input
     * @return
     */
    fun decompress(input: ByteArray?): ByteArray {
        var ref: ByteArrayOutputStream? = null
        try {
            ByteArrayInputStream(input).use { bais ->
                BZip2CompressorInputStream(bais).use { bzip2cis ->
                    ByteArrayOutputStream().use { baos ->
                        IOUtils.copy(bzip2cis, baos)
                        ref = baos
                    }
                }
            }
        } catch (e: IOException) {
            logger.error("failed to decode.", e)
            throw RuntimeException(e)
        }
        return ref!!.toByteArray()
    }
}
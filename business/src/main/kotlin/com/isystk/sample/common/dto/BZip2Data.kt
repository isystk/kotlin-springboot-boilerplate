package com.isystk.sample.common.dto

import com.isystk.sample.common.util.CompressUtils
import org.apache.commons.codec.binary.Base64
import org.seasar.doma.Domain
import java.io.Serializable

@Domain(valueType = ByteArray::class)
class BZip2Data : Serializable {
    var data: ByteArray? = null
    var bzip2: ByteArray? = null

    constructor() {}

    // ResultSet.getBytes(int)で取得された値がこのコンストラクタで設定される
    internal constructor(bytes: ByteArray?) {
        if (data == null) {
            data = CompressUtils.decompress(bytes)
        }
    }

    // PreparedStatement.setBytes(int, bytes)へ設定する値がこのメソッドから取得される
    val value: ByteArray?
        get() {
            if (bzip2 == null) {
                bzip2 = CompressUtils.compress(data)
            }
            return bzip2
        }

    fun toBase64(): String {
        return Base64.encodeBase64String(data)
    }

    companion object {
        private const val serialVersionUID = -4805556024192461766L

        /**
         * ファクトリメソッド
         *
         * @param bytes
         * @return
         */
        fun of(bytes: ByteArray?): BZip2Data {
            val bZip2Data = BZip2Data()
            bZip2Data.data = bytes
            return bZip2Data
        }
    }
}
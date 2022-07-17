package com.isystk.sample.common.dto

import org.apache.commons.lang3.StringUtils
import org.seasar.doma.Domain
import java.io.Serializable

@Domain(valueType = String::class, factoryMethod = "of")
class CommaSeparatedString : Serializable {
    // PreparedStatement.setBytes(int, bytes)へ設定する値がこのメソッドから取得される
    var value: String? = null
        get() = field

    constructor() {}

    // ResultSet.getBytes(int)で取得された値がこのコンストラクタで設定される
    internal constructor(data: String) {
        value = StringUtils.join(data, ",")
    }

    companion object {
        private const val serialVersionUID = -6864852815920199569L

        /**
         * ファクトリメソッド
         *
         * @param data
         * @return
         */
        @JvmStatic
        fun of(data: String): CommaSeparatedString {
            val css = CommaSeparatedString()
            css.value = StringUtils.join(data, ",")
            return css
        }
    }
}
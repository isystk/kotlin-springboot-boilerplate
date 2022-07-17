package com.isystk.sample.common.values

/**
 * 性別
 */
enum class Gender(override val code: String, override val text: String) : Values<Any?> {
    WOMAN("1", "女性"), MAN("2", "男性");

    companion object {
        /**
         * コードに一致する値を取得します。
         *
         * @param code
         * @return
         */
        fun getValue(code: String?): Gender? {
            if (code == null) {
                return null
            }
            for (div in values()) {
                if (div.code == code) {
                    return div
                }
            }
            return null
        }
    }
}
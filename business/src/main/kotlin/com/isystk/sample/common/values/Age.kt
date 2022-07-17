package com.isystk.sample.common.values

/**
 * 年齢
 */
enum class Age(override val code: String, override val text: String) : Values<Any?> {
    UNDER_19("1", "～19歳"), OVER_20("2", "20歳～29歳"), OVER_30("3", "30歳～39歳"), OVER_40("4", "40歳～49歳"), OVER_50("5", "50歳～59歳"), OVER_60("6", "60歳～");

    companion object {
        /**
         * コードに一致する値を取得します。
         *
         * @param code
         * @return
         */
        fun getValue(code: String?): Age? {
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
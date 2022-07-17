package com.isystk.sample.common.values

/**
 * ユーザーステータス
 */
enum class UserStatus(override val code: String, override val text: String) : Values<Any?> {
    TEMPORARY("0", "仮登録"), VALID("1", "有効"), INVALID("2", "利用停止"), WITHDRAW("3", "退会者");

    companion object {
        /**
         * コードに一致する値を取得します。
         *
         * @param code
         * @return
         */
        fun getValue(code: String?): UserStatus? {
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
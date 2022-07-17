package com.isystk.sample.common.values

/**
 * メールテンプレート区分
 */
enum class MailTemplateGroup(override val code: String, override val text: String) : Values<Any?> {
    ENTRY_REGIST("1", "会員登録"), STOCK_PAYMENT("2", "商品決算");

    companion object {
        /**
         * コードに一致する値を取得します。
         *
         * @param code
         * @return
         */
        fun getValue(code: String): MailTemplateGroup? {
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
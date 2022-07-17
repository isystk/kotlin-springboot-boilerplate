package com.isystk.sample.common.values

/**
 * メールテンプレート
 */
enum class MailTemplateDiv(override val code: String, override val text: String, val div: MailTemplateGroup) : Values<Any?> {
    ENTRY_REGIST_TEMPORARY("1", "仮会員登録通知メール", MailTemplateGroup.ENTRY_REGIST), ENTRY_REGIST_VALID("2", "本登録完了通知メール", MailTemplateGroup.ENTRY_REGIST), ENTRY_REMIND("3", "新パスワード設定画面のお知らせメール", MailTemplateGroup.ENTRY_REGIST), STOCK_PAYMENT_COMPLETE("4", "商品購入完了通知メール", MailTemplateGroup.STOCK_PAYMENT);

    companion object {
        /**
         * コードに一致する値を取得します。
         *
         * @param code
         * @return
         */
        fun getValue(code: String?): MailTemplateDiv? {
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
package com.isystk.sample.common.values

/**
 * 画像タイプ
 */
enum class ImageType(override val code: String, override val text: String) : Values<Any?> {
    UNKNOWN("0", "不明"), STOCK("1", "商品"), CONTACT("2", "お問い合わせ");

    companion object {
        /**
         * コードに一致する値を取得します。
         *
         * @param code
         * @return
         */
        fun getValue(code: String?): ImageType? {
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
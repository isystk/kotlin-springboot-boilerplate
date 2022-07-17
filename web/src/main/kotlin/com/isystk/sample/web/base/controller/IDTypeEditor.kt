package com.isystk.sample.web.base.controller

import com.isystk.sample.common.dto.ID
import java.beans.PropertyEditorSupport

/**
 * 文字列をIDクラスに変換する
 */
class IDTypeEditor : PropertyEditorSupport() {
    @Throws(IllegalArgumentException::class)
    override fun setAsText(text: String) {
        try {
            val id = Integer.valueOf(text)
            value = ID.of<Any>(id)
        } catch (e: NumberFormatException) {
            // nop
        }
    }
}
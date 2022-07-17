package com.isystk.sample.web.base.controller.html

import com.isystk.sample.common.dto.ID
import com.isystk.sample.web.base.controller.IDTypeEditor
import org.slf4j.LoggerFactory
import org.springframework.beans.propertyeditors.StringTrimmerEditor
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.InitBinder
import javax.servlet.http.HttpServletRequest

/**
 * HTMLコントローラーアドバイス
 */
@ControllerAdvice(assignableTypes = [AbstractHtmlController::class]) // RestControllerでは動作させない
class HtmlControllerAdvice {
    @InitBinder
    fun initBinder(binder: WebDataBinder, request: HttpServletRequest?) {
        // 文字列フィールドが未入力の場合に、空文字ではなくNULLをバインドする
        binder.registerCustomEditor(String::class.java, StringTrimmerEditor(true))

        // 文字列をIDに変換する
        binder.registerCustomEditor(ID::class.java, IDTypeEditor())
        //
//        // idカラムを入力値で上書きしない
//        binder.setDisallowedFields("*.id");

        // versionカラムを入力値で上書きしない
        binder.setDisallowedFields("*.version")
    }

    companion object {
        private val log = LoggerFactory.getLogger(HtmlControllerAdvice::class.java)
    }
}
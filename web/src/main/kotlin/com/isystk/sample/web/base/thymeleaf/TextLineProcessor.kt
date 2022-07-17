package com.isystk.sample.web.base.thymeleaf

import org.thymeleaf.context.ITemplateContext
import org.thymeleaf.engine.AttributeName
import org.thymeleaf.model.IProcessableElementTag
import org.thymeleaf.model.ITemplateEvent
import org.thymeleaf.processor.element.IElementTagStructureHandler
import org.thymeleaf.standard.processor.AbstractStandardExpressionAttributeTagProcessor
import org.thymeleaf.templatemode.TemplateMode

/**
 * 改行コードをbrタグに変換するプロセッサ
 */
class TextLineProcessor(dialectPrefix: String?, precedence: Int) : AbstractStandardExpressionAttributeTagProcessor(TemplateMode.HTML, dialectPrefix, ATTR_NAME, precedence, true) {
    override fun doProcess(context: ITemplateContext, tag: IProcessableElementTag, attributeName: AttributeName,
                           attributeValue: String, expressionResult: Any, structureHandler: IElementTagStructureHandler) {
        // nullの場合は何も出力せず処理を終了
        if (expressionResult == null) return
        val factory = context.modelFactory
        val model = factory.createModel()
        val brTag: ITemplateEvent = factory.createOpenElementTag("br")

        // 改行コードで分割
        val lines = expressionResult.toString().split("\r\n|\r|\n".toRegex()).toTypedArray()
        for (line in lines) {
            // <br>+1行分の文字列をmodelに追加
            model.add(brTag)
            model.add(factory.createText(line))
        }
        // 先頭の<br>タグを除去
        model.remove(0)
        structureHandler.setBody(model, false)
    }

    companion object {
        const val ATTR_NAME = "textbr"
    }
}
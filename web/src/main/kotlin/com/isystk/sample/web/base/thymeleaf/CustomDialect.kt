package com.isystk.sample.web.base.thymeleaf

import org.thymeleaf.dialect.AbstractProcessorDialect
import org.thymeleaf.processor.IProcessor

/**
 * 独自定義したダイアレクトを登録する
 */
class CustomDialect : AbstractProcessorDialect(DIALECT_NAME, DIALECT_PREFIX, 1000) {
    override fun getProcessors(dialectPrefix: String): Set<IProcessor> {
        val processors: MutableSet<IProcessor> = HashSet()
        processors.add(TextLineProcessor(dialectPrefix, dialectProcessorPrecedence))
        return processors
    }

    companion object {
        const val DIALECT_NAME = "CustomDialect"
        const val DIALECT_PREFIX = "ex"
    }
}
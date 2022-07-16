package com.isystk.sample.batch.jobs.importMst

import com.isystk.sample.common.validator.AbstractValidator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class ImportMstStockValidator : AbstractValidator<ImportMstStockDto?>() {
    override fun doValidate(dto: ImportMstStockDto?, errors: Errors?) {
        if (dto != null) {
            if (dto.position == 1 && dto.name != "商品名") {
                errors?.rejectValue("name", "importMstJob.invalidMstStock")
            }
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(
                ImportMstStockValidator::class.java)
    }
}
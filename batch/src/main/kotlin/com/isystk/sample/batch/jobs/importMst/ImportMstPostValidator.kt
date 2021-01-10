package com.isystk.sample.batch.jobs.importMst

import com.isystk.sample.common.validator.AbstractValidator
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import org.springframework.validation.Errors

@Component
class ImportMstPostValidator : AbstractValidator<ImportMstPostDto?>() {

    companion object {
        private val log = LoggerFactory.getLogger(ImportMstPostValidator::class.java)
    }

    override fun doValidate(form: ImportMstPostDto?, errors: Errors?) {
        if (form!!.position == 1 && form.postTagId != "Wyn") {
            errors!!.rejectValue("postTagId", "importMstJob.invalidMstPostId")
        }
    }
}
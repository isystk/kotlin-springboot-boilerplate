package com.isystk.sample.batch.controller.html.post

import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.web.base.controller.html.BaseSearchForm
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate
import javax.validation.constraints.Digits

class PostSearchForm : BaseSearchForm(), Pageable {
    var postId: @Digits(integer = 9, fraction = 0) Int? = null
    var userId: @Digits(integer = 9, fraction = 0) Int? = null
    var title: String? = null

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var registDateFrom: LocalDate? = null

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var registDateTo: LocalDate? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}
package com.isystk.sample.web.admin.controller.html.user

import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.web.base.controller.html.BaseSearchForm
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDate

class UserListForm : BaseSearchForm(), Pageable {

    var name: String? = null

    var email: String? = null

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var createdAtFrom: LocalDate? = null

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    var createdAtTo: LocalDate? = null

    companion object {
        private const val serialVersionUID = 7593564324192730932L
    }
}
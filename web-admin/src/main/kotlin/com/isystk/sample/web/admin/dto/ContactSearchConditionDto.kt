package com.isystk.sample.web.admin.dto

import java.time.LocalDate

class ContactSearchConditionDto {
    var userName: String? = null
    var createdAtFrom: LocalDate? = null
    var createdAtTo: LocalDate? = null
}
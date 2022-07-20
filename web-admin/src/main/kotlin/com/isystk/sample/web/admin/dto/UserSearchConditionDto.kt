package com.isystk.sample.web.admin.dto

import java.time.LocalDate

class UserSearchConditionDto {

    var name: String? = null

    var email: String? = null

    var createdAtFrom: LocalDate? = null

    var createdAtTo: LocalDate? = null
    
}
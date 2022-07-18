package com.isystk.sample.web.admin.security

import com.isystk.sample.domain.entity.Admin
import com.isystk.sample.web.base.filter.UserIdAware
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class LoginStaff(// 管理者情報。
        private val staff: Admin?, authorities: List<GrantedAuthority?>?) : User(staff!!.email, staff.password, true, true, true, true, authorities), UserIdAware {
    fun Staff(): Admin? {
        return staff
    }

    override val userId: String?
        get() = staff?.id.toString()

    override val userName: String?
        get() = staff?.name.toString()

}
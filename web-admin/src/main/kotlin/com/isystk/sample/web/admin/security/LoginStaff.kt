package com.isystk.sample.web.admin.security

import com.isystk.sample.domain.entity.TStaff
import com.isystk.sample.web.base.filter.UserIdAware
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class LoginStaff(// 管理者情報。
        private val staff: TStaff?, authorities: List<GrantedAuthority?>?) : User(staff!!.email, staff.password, true, true, true, true, authorities), UserIdAware {
    fun Staff(): TStaff? {
        return staff
    }

    override fun getUserId(): String {
        return staff!!.staffId.toString()
    }

    override fun getUserName(): String {
        return staff!!.familyName.toString() + " " + staff.name.toString()
    }

}
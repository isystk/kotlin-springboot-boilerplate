package com.isystk.sample.web.front.security

import com.isystk.sample.domain.entity.TUser
import com.isystk.sample.web.base.filter.UserIdAware
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.User

class LoginUser(// ユーザー情報。
        private val user: TUser?, authorities: List<GrantedAuthority?>?) : User(user!!.email, user.password, true, true, true, true, authorities), UserIdAware {
    fun User(): TUser? {
        return user
    }

    override fun getUserId(): String {
        return user!!.userId.toString()
    }

    override fun getUserName(): String {
        return user!!.familyName.toString() + " " + user.name.toString()
    }

}
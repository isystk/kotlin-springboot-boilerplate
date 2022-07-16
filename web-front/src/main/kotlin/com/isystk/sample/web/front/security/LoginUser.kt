package com.isystk.sample.web.front.security

import com.isystk.sample.domain.entity.User
import com.isystk.sample.web.base.filter.UserIdAware
import org.springframework.security.core.GrantedAuthority

class LoginUser(// ユーザー情報。
        private val user: User?, authorities: List<GrantedAuthority?>?) : org.springframework.security.core.userdetails.User(user!!.email, user.password, true, true, true, true, authorities)
        , UserIdAware
        {
    fun User(): User? {
        return user
    }

    override val userId: String?
        get() = user!!.id.toString()

    override val userName: String?
        get() = user!!.name.toString()

}
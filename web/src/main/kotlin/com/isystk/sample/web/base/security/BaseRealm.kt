package com.isystk.sample.web.base.security

import org.slf4j.LoggerFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException

/**
 * 基底レルム
 */
abstract class BaseRealm : UserDetailsService {
    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(loginId: String): UserDetails {
        var user: UserDetails? = null
        user = try {
            getLoginUser(loginId)
        } catch (e: Throwable) {
            throw UsernameNotFoundException("failed to find login user.")
        }
        if (user == null) {
            throw UsernameNotFoundException("no user found. [login_id=$loginId]")
        }
        return user
    }

    protected abstract fun getLoginUser(loginId: String?): UserDetails?

    companion object {
        private val log = LoggerFactory.getLogger(BaseRealm::class.java)
    }
}
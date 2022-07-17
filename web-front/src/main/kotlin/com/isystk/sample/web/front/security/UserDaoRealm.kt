package com.isystk.sample.web.front.security

import com.isystk.sample.domain.dao.UserDao
import com.isystk.sample.domain.dto.UserCriteria
import com.isystk.sample.domain.entity.User
import com.isystk.sample.web.base.security.BaseRealm
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

/**
 * ログインサービス
 */
@Component
class UserDaoRealm : BaseRealm() {
    @Autowired
    var userDao: UserDao? = null
    override fun getLoginUser(email: String?): UserDetails {
        var user: User? = null
        var authorityList: List<GrantedAuthority?>? = null
        return try {
            // login_idをメールアドレスと見立てる
            val criteria = UserCriteria()
            criteria.emailEq = email

            // 担当者を取得して、セッションに保存する
            user = userDao!!.findOne(criteria)
                    .orElseThrow { UsernameNotFoundException("no user found [id=$email]") }

            // 役割と権限を両方ともGrantedAuthorityとして渡す
            authorityList = AuthorityUtils.createAuthorityList()
            LoginUser(user, authorityList)
        } catch (e: Exception) {
            if (e !is UsernameNotFoundException) {
                // 入力間違い以外の例外はログ出力する
                log.error("failed to getLoginUser. ", e)
                throw e
            }
            throw UsernameNotFoundException("could not select user.", e)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(UserDaoRealm::class.java)
    }
}
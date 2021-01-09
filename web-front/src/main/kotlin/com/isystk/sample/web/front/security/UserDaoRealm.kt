package com.isystk.sample.web.front.security

import com.isystk.sample.domain.dao.TUserDao
import com.isystk.sample.domain.dto.TUserCriteria
import com.isystk.sample.domain.entity.TUser
import com.isystk.sample.web.base.security.BaseRealm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import java.util.*

/**
 * ログインサービス
 */
@Component
class UserDaoRealm : BaseRealm() {

    @Autowired
    var tUserDao: TUserDao? = null
    override fun getLoginUser(email: String): UserDetails {
        var user: TUser? = null
        var authorityList: List<GrantedAuthority?>? = null
        return try {
            // login_idをメールアドレスと見立てる
            val criteria = TUserCriteria()
            criteria.emailEq = email

            // 担当者を取得して、セッションに保存する
            user = tUserDao!!.findOne(criteria)
                    .orElseThrow { UsernameNotFoundException("no user found [id=$email]") }

            // 役割と権限を両方ともGrantedAuthorityとして渡す
            authorityList = AuthorityUtils.createAuthorityList(*HashSet<String>().toTypedArray<String>())
            LoginUser(user, authorityList)
        } catch (e: Exception) {
            if (e !is UsernameNotFoundException) {
//                // 入力間違い以外の例外はログ出力する
//                Log.error("failed to getLoginUser. ", e)
                throw e
            }
            throw UsernameNotFoundException("could not select user.", e)
        }
    }
}
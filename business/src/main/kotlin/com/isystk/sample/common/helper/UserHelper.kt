package com.isystk.sample.common.helper

import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.domain.dao.AuditInfoHolder
import com.isystk.sample.domain.dao.UserDao
import com.isystk.sample.domain.dto.UserCriteria
import com.isystk.sample.domain.entity.User
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.math.BigInteger

/**
 * ユーザーヘルパー
 */
@Component
class UserHelper {
    @Autowired
    var userDao: UserDao? = null

    /**
     * ユーザーを全件取得します。
     *
     * @return
     */
    val userList: List<User>
        get() {
            val criteria = UserCriteria()
            criteria.isDeleteFlgFalse = true
            return userDao!!.findAll(criteria)
        }

    /**
     * ユーザーを取得します。
     *
     * @return
     */
    fun getUser(userId: BigInteger): User {
        val criteria = UserCriteria()
        criteria.idEq = userId
        return userDao!!.findOne(criteria).orElseThrow { NoDataFoundException("userId=" + userId + "のデータが見つかりません。") }
    }

    /**
     * ログインユーザーを取得します。
     *
     * @return
     */
    val loginUserId: BigInteger
        get() = user.id

    /**
     * ログインユーザーを取得します。
     *
     * @return
     */
    val user: User
        get() {
            val criteria = UserCriteria()
            criteria.emailEq = AuditInfoHolder.getAuditUser()
            return userDao!!.findOne(criteria).orElseThrow {
                NoDataFoundException(
                        "email=" + AuditInfoHolder.getAuditUser() + "のデータが見つかりません。")
            }
        }

    /**
     * 最終ログイン日時を更新します。
     *
     * @return
     */
    fun updateLastLogin() {
        val user = user
        user.lastLoginAt = DateUtils.now
        userDao!!.update(user)
    }

    companion object {
        private val log = LoggerFactory.getLogger(UserHelper::class.java)
    }
}
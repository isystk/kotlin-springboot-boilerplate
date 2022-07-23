package com.isystk.sample.common.helper

import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.domain.dao.AdminDao
import com.isystk.sample.domain.dao.AuditInfoHolder
import com.isystk.sample.domain.dto.AdminCriteria
import com.isystk.sample.domain.entity.Admin
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * 管理者ヘルパー
 */
@Component("staff")
class StaffHelper {
    @Autowired
    var adminDao: AdminDao? = null

    /**
     * ログイン済みかどうか
     */
    val isLogined: Boolean
        get() = AuditInfoHolder.auditUser != null

    /**
     * ログインユーザーを取得します。
     *
     * @return
     */
    val loginStaffId: Long
        get() = loginStaff.id!!

    /**
     * ログインユーザーを取得します。
     *
     * @return
     */
    val loginStaff: Admin
        get() {
            val criteria = AdminCriteria()
            criteria.emailEq = AuditInfoHolder.auditUser
            return adminDao!!.findOne(criteria) ?: throw
                NoDataFoundException(
                        "email=" + AuditInfoHolder.auditUser + "のデータが見つかりません。")
        }

    /**
     * 最終ログイン日時を更新します。
     *
     * @return
     */
    fun updateLastLogin() {
        val admin = loginStaff
        admin.lastLoginAt = DateUtils.now
        adminDao!!.update(admin)
    }

}
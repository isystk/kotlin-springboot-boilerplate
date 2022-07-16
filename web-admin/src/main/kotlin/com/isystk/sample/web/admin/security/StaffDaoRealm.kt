package com.isystk.sample.web.admin.security

import com.isystk.sample.domain.dao.AdminDao
import com.isystk.sample.domain.dto.AdminCriteria
import com.isystk.sample.domain.entity.Admin
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
class StaffDaoRealm : BaseRealm() {
    @Autowired
    var adminDao: AdminDao? = null
    override fun getLoginUser(email: String): UserDetails {
        var staff: Admin? = null
        var authorityList: List<GrantedAuthority?>? = null
        return try {
            // login_idをメールアドレスと見立てる
            val criteria = AdminCriteria()
            criteria.emailEq = email

            // 担当者を取得して、セッションに保存する
            staff = adminDao!!.findOne(criteria)
                    .orElseThrow { UsernameNotFoundException("no staff found [id=$email]") }
            //
//            // 担当者権限を取得する
//            List<StaffRole> staffRoles = staffRoleDao.selectByStaffId(staff.getId(), toList());
//
//            // 役割キーにプレフィックスをつけてまとめる
//            Set<String> roleKeys = staffRoles.stream().map(StaffRole::getRoleKey).collect(toSet());
//
//            // 権限キーをまとめる
//            Set<String> permissionKeys = staffRoles.stream().map(StaffRole::getPermissionKey).collect(toSet());

            // 役割と権限を両方ともGrantedAuthorityとして渡す
            val authorities: Set<String> = HashSet()
            //            authorities.addAll(roleKeys);
//            authorities.addAll(permissionKeys);
            authorityList = AuthorityUtils.createAuthorityList(*authorities.toTypedArray())
            LoginStaff(staff, authorityList)
        } catch (e: Exception) {
            if (e !is UsernameNotFoundException) {
                // 入力間違い以外の例外はログ出力する
                log.error("failed to getLoginUser. ", e)
                throw e
            }
            throw UsernameNotFoundException("could not select staff.", e)
        }
    }

    companion object {
        private val log = LoggerFactory.getLogger(StaffDaoRealm::class.java)
    }
}
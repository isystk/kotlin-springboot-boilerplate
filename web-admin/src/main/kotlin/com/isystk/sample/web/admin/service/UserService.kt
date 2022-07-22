package com.isystk.sample.web.admin.service

import com.isystk.sample.common.dto.Page
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.domain.dao.UserDao
import com.isystk.sample.domain.dto.UserCriteria
import com.isystk.sample.domain.dto.UserRepositoryDto
import com.isystk.sample.domain.repository.UserRepository
import com.isystk.sample.web.admin.dto.UserSearchConditionDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalTime

@Service
class UserService : BaseTransactionalService() {
    @Autowired
    var userRepository: UserRepository? = null

    @Autowired
    var userDao: UserDao? = null

    /**
     * 顧客を複数取得します。
     *
     * @param dto
     * @return
     */
    fun findAll(dto: UserSearchConditionDto): List<UserRepositoryDto> {
        return userRepository!!.findAll(dtoToCriteria(dto))
    }

    /**
     * 顧客を複数取得します。(ページングあり)
     *
     * @param dto
     * @param pageable
     * @return
     */
    fun findPage(dto: UserSearchConditionDto, pageable: Pageable): Page<UserRepositoryDto> {
        return userRepository!!.findPage(dtoToCriteria(dto), pageable)
    }

    /**
     * 検索条件を詰める
     *
     * @return
     */
    private fun dtoToCriteria(
            dto: UserSearchConditionDto): UserCriteria {
        // 入力値を詰め替える
        val criteria = UserCriteria()
        criteria.nameLike = dto.name
        criteria.emailLike = dto.email
        criteria.createdAtGe = dto.createdAtFrom?.atStartOfDay()
        criteria.createdAtLe = dto.createdAtTo?.atTime(LocalTime.MAX)
        criteria.isDeleteFlgFalse = true
        criteria.orderBy = "order by updated_at desc"
        return criteria
    }

    /**
     * 顧客を取得します。
     *
     * @param userId
     * @return
     */
    fun findById(userId: Long): UserRepositoryDto {
        return userRepository!!.findById(userId)
    }
}
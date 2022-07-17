package com.isystk.sample.domain.repository

import com.google.common.collect.Lists
import com.isystk.sample.common.dto.Page
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.exception.NoDataFoundException
import com.isystk.sample.common.service.BaseRepository
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dao.UserDao
import com.isystk.sample.domain.dto.UserCriteria
import com.isystk.sample.domain.dto.UserRepositoryDto
import com.isystk.sample.domain.entity.User
import com.isystk.sample.domain.util.DomaUtils
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.math.BigInteger
import java.util.*
import java.util.stream.Collectors

/**
 * ユーザーリポジトリ
 */
@Repository
class UserRepository : BaseRepository() {
    @Autowired
    var userDao: UserDao? = null

    /**
     * 顧客を複数取得します。
     *
     * @param criteria
     * @return
     */
    fun findAll(criteria: UserCriteria?): List<UserRepositoryDto> {
        val options = DomaUtils.createSelectOptions(1, Int.MAX_VALUE)
        return convertDto(userDao!!.findAll(criteria, options, Collectors.toList()))
    }

    /**
     * 顧客を複数取得します。(ページングあり)
     *
     * @param criteria
     * @param pageable
     * @return
     */
    fun findPage(criteria: UserCriteria?, pageable: Pageable): Page<UserRepositoryDto> {
        val options = DomaUtils.createSelectOptions(pageable)
        val userList = convertDto(userDao!!.findAll(criteria, options.count(), Collectors.toList()))
        return pageFactory.create(userList, pageable, options.count)
    }

    /**
     * RepositoryDto に変換します。
     *
     * @param tUserList
     * @return
     */
    private fun convertDto(tUserList: List<User>): List<UserRepositoryDto> {
        return ObjectMapperUtils.mapAll(tUserList, UserRepositoryDto::class.java)
    }

    /**
     * 顧客を取得します。
     *
     * @param criteria
     * @return
     */
    fun findOne(criteria: UserCriteria): Optional<UserRepositoryDto> {
        val data = userDao!!.findOne(criteria)
                .orElseThrow { NoDataFoundException(criteria.toString() + "のデータが見つかりません。") }
        return Optional.ofNullable(convertDto(Lists.newArrayList(data))[0])
    }

    /**
     * 顧客を取得します。
     *
     * @return
     */
    fun findById(id: BigInteger): UserRepositoryDto {
        val data = userDao!!.selectById(id)
                .orElseThrow { NoDataFoundException("user_id=$id のデータが見つかりません。") }
        return convertDto(Lists.newArrayList(data))[0]
    }

    /**
     * ユーザーを追加します。
     *
     * @param inputUser
     * @return
     */
    fun create(inputUser: User): User {

        // 1件登録
        val time = DateUtils.getNow()
        inputUser.createdAt = time // 作成日
        inputUser.updatedAt = time // 更新日
        inputUser.deleteFlg = false // 削除フラグ
        inputUser.version = 0L // 楽観ロック改定番号
        userDao!!.insert(inputUser)
        return inputUser
    }

    /**
     * ユーザーを更新します。
     *
     * @param inputUser
     * @return
     */
    fun update(inputUser: User): User {
        // 1件更新
        val time = DateUtils.getNow()
        inputUser.updatedAt = time // 更新日
        val updated = userDao!!.update(inputUser)
        if (updated < 1) {
            throw NoDataFoundException("user_id=" + inputUser.id + " のデータが見つかりません。")
        }
        return inputUser
    }

    /**
     * ユーザーを論理削除します。
     *
     * @return
     */
    fun delete(id: BigInteger): User {
        val user = userDao!!.selectById(id)
                .orElseThrow { NoDataFoundException("user_id=$id のデータが見つかりません。") }
        val updated = userDao!!.delete(user)
        if (updated < 1) {
            throw NoDataFoundException("user_id=$id は更新できませんでした。")
        }
        return user
    }
}
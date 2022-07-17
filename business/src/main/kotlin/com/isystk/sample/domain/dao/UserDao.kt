package com.isystk.sample.domain.dao

import com.isystk.sample.domain.dto.UserCriteria
import com.isystk.sample.domain.entity.User
import org.seasar.doma.*
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.SelectOptions
import java.math.BigInteger
import java.util.*
import java.util.stream.Collector

/**
 */
@ConfigAutowireable
@Dao
interface UserDao {
    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    fun insert(entity: User): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    fun update(entity: User): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    fun delete(entity: User): Int

    /**
     * @param criteria
     * @param options
     * @return
     */
    @Select(strategy = SelectType.COLLECT)
    fun <R> findAll(criteria: UserCriteria, options: SelectOptions, collector: Collector<User, *, R>): R

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findAll(criteria: UserCriteria): List<User>

    /**
     * @param id
     * @return the User entity
     */
    @Select
    fun selectById(id: BigInteger): Optional<User>

    /**
     * @param id
     * @param version
     * @return the User entity
     */
    @Select(ensureResult = true)
    fun selectByIdAndVersion(id: BigInteger, version: Long): Optional<User>

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findOne(criteria: UserCriteria): Optional<User>
}
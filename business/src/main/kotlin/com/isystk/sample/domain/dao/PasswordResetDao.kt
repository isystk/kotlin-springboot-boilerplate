package com.isystk.sample.domain.dao

import com.isystk.sample.domain.dto.PasswordResetCriteria
import com.isystk.sample.domain.entity.PasswordReset
import org.seasar.doma.*
import org.seasar.doma.Suppress
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.SelectOptions
import org.seasar.doma.message.Message
import java.util.*
import java.util.stream.Collector

/**
 */
@ConfigAutowireable
@Dao
@Suppress(messages = [Message.DOMA4220])
interface PasswordResetDao {
    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    fun insert(entity: PasswordReset): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    fun update(entity: PasswordReset): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    fun delete(entity: PasswordReset): Int

    /**
     * @param criteria
     * @param options
     * @return
     */
    @Select(strategy = SelectType.COLLECT)
    fun <R> findAll(criteria: PasswordResetCriteria, options: SelectOptions, collector: Collector<PasswordReset, *, R>): R

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findAll(criteria: PasswordResetCriteria): List<PasswordReset>

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findOne(criteria: PasswordResetCriteria): Optional<PasswordReset>
}
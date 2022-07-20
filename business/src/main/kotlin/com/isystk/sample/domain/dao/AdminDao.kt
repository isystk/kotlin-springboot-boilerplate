package com.isystk.sample.domain.dao

import com.isystk.sample.domain.dto.AdminCriteria
import com.isystk.sample.domain.entity.Admin
import org.seasar.doma.*
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.SelectOptions
import java.util.*
import java.util.stream.Collector

/**
 */
@ConfigAutowireable
@Dao
interface AdminDao {
    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    fun insert(entity: Admin): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    fun update(entity: Admin): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    fun delete(entity: Admin): Int

    /**
     * @param criteria
     * @param options
     * @return
     */
    @Select(strategy = SelectType.COLLECT)
    fun <R> findAll(criteria: AdminCriteria, options: SelectOptions, collector: Collector<Admin, *, R>): R

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findAll(criteria: AdminCriteria): List<Admin>

    /**
     * @param id
     * @return the Admin entity
     */
    @Select
    fun selectById(id: Long): Admin?

    /**
     * @param id
     * @param version
     * @return the Admin entity
     */
    @Select(ensureResult = true)
    fun selectByIdAndVersion(id: Long, version: Long): Admin?

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findOne(criteria: AdminCriteria): Admin?
}
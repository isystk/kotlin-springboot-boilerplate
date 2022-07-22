package com.isystk.sample.domain.dao

import com.isystk.sample.domain.dto.CartCriteria
import com.isystk.sample.domain.entity.Cart
import org.seasar.doma.*
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.SelectOptions
import java.util.*
import java.util.stream.Collector

/**
 */
@ConfigAutowireable
@Dao
interface CartDao {
    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    fun insert(entity: Cart): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    fun update(entity: Cart): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    fun delete(entity: Cart): Int

    /**
     * @param criteria
     * @param options
     * @return
     */
    @Select(strategy = SelectType.COLLECT)
    fun <R> findAll(criteria: CartCriteria, options: SelectOptions, collector: Collector<Cart, *, R>): R

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findAll(criteria: CartCriteria): List<Cart>

    /**
     * @param id
     * @return the Cart entity
     */
    @Select
    fun selectById(id: Long): Cart?

    /**
     * @param id
     * @param version
     * @return the Cart entity
     */
    @Select(ensureResult = true)
    fun selectByIdAndVersion(id: Long, version: Long): Cart?

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findOne(criteria: CartCriteria): Cart?
}
package com.isystk.sample.domain.dao

import com.isystk.sample.domain.dto.OrderHistoryCriteria
import com.isystk.sample.domain.entity.OrderHistory
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
interface OrderHistoryDao {
    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    fun insert(entity: OrderHistory): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    fun update(entity: OrderHistory): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    fun delete(entity: OrderHistory): Int

    /**
     * @param criteria
     * @param options
     * @return
     */
    @Select(strategy = SelectType.COLLECT)
    fun <R> findAll(criteria: OrderHistoryCriteria, options: SelectOptions, collector: Collector<OrderHistory, *, R>): R

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findAll(criteria: OrderHistoryCriteria): List<OrderHistory>

    /**
     * @param id
     * @return the OrderHistory entity
     */
    @Select
    fun selectById(id: BigInteger): OrderHistory?

    /**
     * @param id
     * @param version
     * @return the OrderHistory entity
     */
    @Select(ensureResult = true)
    fun selectByIdAndVersion(id: BigInteger, version: Long): OrderHistory?

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findOne(criteria: OrderHistoryCriteria): OrderHistory?
}
package com.isystk.sample.domain.dao

import com.isystk.sample.domain.dto.StockCriteria
import com.isystk.sample.domain.entity.Stock
import org.seasar.doma.*
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.SelectOptions
import java.util.*
import java.util.stream.Collector

/**
 */
@ConfigAutowireable
@Dao
interface StockDao {
    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    fun insert(entity: Stock): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    fun update(entity: Stock): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    fun delete(entity: Stock): Int

    /**
     * @param criteria
     * @param options
     * @return
     */
    @Select(strategy = SelectType.COLLECT)
    fun <R> findAll(criteria: StockCriteria, options: SelectOptions, collector: Collector<Stock, *, R>): R

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findAll(criteria: StockCriteria): List<Stock>

    /**
     * @param id
     * @return the Stock entity
     */
    @Select
    fun selectById(id: Long): Stock?

    /**
     * @param id
     * @param version
     * @return the Stock entity
     */
    @Select(ensureResult = true)
    fun selectByIdAndVersion(id: Long, version: Long): Stock?

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findOne(criteria: StockCriteria): Stock?
}
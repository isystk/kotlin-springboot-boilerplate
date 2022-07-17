package com.isystk.sample.domain.dao

import com.isystk.sample.domain.dto.ContactFormImageCriteria
import com.isystk.sample.domain.entity.ContactFormImage
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
interface ContactFormImageDao {
    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    fun insert(entity: ContactFormImage): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    fun update(entity: ContactFormImage): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    fun delete(entity: ContactFormImage): Int

    /**
     * @param criteria
     * @param options
     * @return
     */
    @Select(strategy = SelectType.COLLECT)
    fun <R> findAll(criteria: ContactFormImageCriteria, options: SelectOptions, collector: Collector<ContactFormImage, *, R>): R

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findAll(criteria: ContactFormImageCriteria): List<ContactFormImage>

    /**
     * @param id
     * @return the ContactFormImage entity
     */
    @Select
    fun selectById(id: BigInteger): Optional<ContactFormImage>

    /**
     * @param id
     * @param version
     * @return the ContactFormImage entity
     */
    @Select(ensureResult = true)
    fun selectByIdAndVersion(id: BigInteger, version: Long): Optional<ContactFormImage>

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findOne(criteria: ContactFormImageCriteria): Optional<ContactFormImage>
}
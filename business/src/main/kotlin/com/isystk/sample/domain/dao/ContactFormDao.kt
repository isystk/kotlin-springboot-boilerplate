package com.isystk.sample.domain.dao

import com.isystk.sample.domain.dto.ContactFormCriteria
import com.isystk.sample.domain.entity.ContactForm
import org.seasar.doma.*
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.SelectOptions
import java.util.*
import java.util.stream.Collector

/**
 */
@ConfigAutowireable
@Dao
interface ContactFormDao {
    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    fun insert(entity: ContactForm): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    fun update(entity: ContactForm): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    fun delete(entity: ContactForm): Int

    /**
     * @param criteria
     * @param options
     * @return
     */
    @Select(strategy = SelectType.COLLECT)
    fun <R> findAll(criteria: ContactFormCriteria, options: SelectOptions, collector: Collector<ContactForm, *, R>): R

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findAll(criteria: ContactFormCriteria): List<ContactForm>

    /**
     * @param id
     * @return the ContactForm entity
     */
    @Select
    fun selectById(id: Long): ContactForm?

    /**
     * @param id
     * @param version
     * @return the ContactForm entity
     */
    @Select(ensureResult = true)
    fun selectByIdAndVersion(id: Long, version: Long): ContactForm?

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findOne(criteria: ContactFormCriteria): ContactForm?
}
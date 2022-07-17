package com.isystk.sample.domain.dao

import com.isystk.sample.domain.dto.MailTemplateCriteria
import com.isystk.sample.domain.entity.MailTemplate
import org.seasar.doma.*
import org.seasar.doma.boot.ConfigAutowireable
import org.seasar.doma.jdbc.SelectOptions
import java.util.*
import java.util.stream.Collector

/**
 */
@ConfigAutowireable
@Dao
interface MailTemplateDao {
    /**
     * @param entity
     * @return affected rows
     */
    @Insert
    fun insert(entity: MailTemplate): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Update
    fun update(entity: MailTemplate): Int

    /**
     * @param entity
     * @return affected rows
     */
    @Delete
    fun delete(entity: MailTemplate): Int

    /**
     * @param criteria
     * @param options
     * @return
     */
    @Select(strategy = SelectType.COLLECT)
    fun <R> findAll(criteria: MailTemplateCriteria, options: SelectOptions, collector: Collector<MailTemplate, *, R>): R

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findAll(criteria: MailTemplateCriteria): List<MailTemplate>

    /**
     * @param mailTemplateId
     * @return the MailTemplate entity
     */
    @Select
    fun selectById(mailTemplateId: Long): Optional<MailTemplate>

    /**
     * @param mailTemplateId
     * @param version
     * @return the MailTemplate entity
     */
    @Select(ensureResult = true)
    fun selectByIdAndVersion(mailTemplateId: Long, version: Long): Optional<MailTemplate>

    /**
     * @param criteria
     * @return
     */
    @Select
    fun findOne(criteria: MailTemplateCriteria): Optional<MailTemplate>
}
package com.isystk.sample.domain.dao

import com.isystk.sample.common.dto.Dto
import com.isystk.sample.common.exception.DoubleSubmitErrorException
import com.isystk.sample.common.util.DateUtils.now
import com.isystk.sample.common.util.ReflectionUtils.findWithAnnotation
import com.isystk.sample.common.util.ReflectionUtils.getFieldValue
import com.isystk.sample.domain.dto.DomaDto
import org.seasar.doma.Id
import org.seasar.doma.jdbc.entity.EntityListener
import org.seasar.doma.jdbc.entity.PreDeleteContext
import org.seasar.doma.jdbc.entity.PreInsertContext
import org.seasar.doma.jdbc.entity.PreUpdateContext
import org.slf4j.LoggerFactory
import java.lang.reflect.Field
import java.util.stream.Collectors

// コンストラクタが必須のため
class DefaultEntityListener<ENTITY> : EntityListener<ENTITY> {
    /**
     * 新規登録
     */
    override fun preInsert(entity: ENTITY, context: PreInsertContext<ENTITY>) {
        // 二重送信防止チェック
        val expected = DoubleSubmitCheckTokenHolder.expectedToken
        val actual = DoubleSubmitCheckTokenHolder.actualToken
        if (expected != null && actual != null && expected != actual) {
            throw DoubleSubmitErrorException()
        }
    }

    /**
     * 更新・論理削除
     */
    override fun preUpdate(entity: ENTITY, context: PreUpdateContext<ENTITY>) {}

    /**
     * 物理削除
     */
    override fun preDelete(entity: ENTITY, context: PreDeleteContext<ENTITY>) {
        if (entity is DomaDto) {
            val domaDto = entity as DomaDto
            val deletedAt = AuditInfoHolder.auditDateTime
            val deletedBy = now
            val name = domaDto.javaClass.name
            val ids = getIds(domaDto)

            // 物理削除した場合はログ出力する
            log.info("データを物理削除しました。entity={}, id={}, deletedBy={}, deletedAt={}", name, ids, deletedBy,
                    deletedAt)
        }
    }

    /**
     * Idアノテーションが付与されたフィールドの値のリストを返します。
     *
     * @param dto
     * @return
     */
    protected fun getIds(dto: Dto): List<Any?> {
        return findWithAnnotation(dto.javaClass, Id::class.java)
                .map { f: Field? -> getFieldValue(f!!, dto) }.collect(Collectors.toList())
    }

    companion object {
        private val log = LoggerFactory.getLogger(DefaultEntityListener::class.java)
    }
}
package com.isystk.sample.batch.jobs.importMst

import com.isystk.sample.batch.context.BatchContext
import com.isystk.sample.batch.context.BatchContextHolder
import com.isystk.sample.batch.item.ItemError
import com.isystk.sample.batch.jobs.BaseTasklet
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.common.util.ValidateUtils
import com.isystk.sample.domain.dao.MPostTagDao
import com.isystk.sample.domain.entity.MPostTag
import org.apache.commons.compress.utils.Lists
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.function.Consumer
import javax.validation.ValidationException

/**
 * ユーザー情報取り込みタスク
 */
class ImportMstPostTasklet : BaseTasklet<ImportMstPostDto?>() {
    @Autowired
    var mPostTagDao: MPostTagDao? = null

    @Throws(IOException::class)
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val status = super.execute(contribution, chunkContext)
        val context = BatchContextHolder.context
        val errors = getErrors(context)
        if (ValidateUtils.isNotEmpty(errors)) {
            errors.forEach(Consumer { e: ItemError? ->
                val sourceName = e!!.sourceName
                val position = e.position
                val errorMessage = e.errorMessage
                log.error("エラーがあります。ファイル名={}, 行数={}, エラーメッセージ={}", sourceName, position, errorMessage)
            })
            throw ValidationException("取り込むファイルに不正な行が含まれています。")
        }
        return status
    }

    override fun doProcess(context: BatchContext?) {
        val path = Paths.get("src/main/resources/tag_mst.csv")
        val importTagDtoList: MutableList<ImportMstPostDto> = Lists.newArrayList()
        try {
            val lines = Files.readAllLines(path, StandardCharsets.UTF_8)
            lines.forEach(Consumer { line: String? ->
                val row = StringUtils.splitByWholeSeparatorPreserveAllTokens(line, ",")
                val dto = ImportMstPostDto()
                dto.sourceName = path.toString()
                dto.postTagId = row[0]
                dto.name = row[1]
                importTagDtoList.add(dto)
            })
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val csvPostTags = ObjectMapperUtils.map<Array<MPostTag>, List<*>>(importTagDtoList, Array<MPostTag>::class.java)
        for (csvPostTag in csvPostTags) {
            val data = mPostTagDao!!.selectById(csvPostTag.postTagId)
            if (data.isEmpty) {
                val mPostTag = ObjectMapperUtils.map(csvPostTag, MPostTag::class.java)
                mPostTag.registTime = DateUtils.getNow()
                mPostTag.updateTime = DateUtils.getNow()
                mPostTag.deleteFlg = false
                mPostTagDao!!.insert(mPostTag)
            } else {
                val mPostTag = data.get()
                mPostTag.name = csvPostTag.name
                mPostTag.updateTime = DateUtils.getNow()
                mPostTagDao!!.update(mPostTag)
            }
        }
    }

    private fun getErrors(context: BatchContext?): List<ItemError?> {
        val additionalInfo = context!!.additionalInfo()
        var errors = additionalInfo!!["errors"] as List<ItemError?>?
        if (errors == null) {
            errors = ArrayList()
        }
        return errors
    }

    companion object {
        private val log = LoggerFactory.getLogger(ImportMstPostTasklet::class.java)
    }
}
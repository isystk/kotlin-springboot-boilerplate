package com.isystk.sample.batch.jobs.importMst

import com.isystk.sample.batch.context.BatchContext
import com.isystk.sample.batch.context.BatchContextHolder
import com.isystk.sample.batch.item.ItemError
import com.isystk.sample.batch.jobs.BaseTasklet
import com.isystk.sample.common.helper.ImageHelper
import com.isystk.sample.common.logger
import com.isystk.sample.common.util.*
import com.isystk.sample.domain.dao.StockDao
import com.isystk.sample.domain.entity.Stock
import org.apache.commons.compress.utils.Lists
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.function.Consumer
import javax.validation.ValidationException

/**
 * ユーザー情報取り込みタスク
 */
class ImportMstStockTasklet : BaseTasklet<ImportMstStockDto?>() {
    @Autowired
    var imageHelper: ImageHelper? = null

    @Autowired
    var stockDao: StockDao? = null
    @Throws(IOException::class)
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        val status = super.execute(contribution, chunkContext)
        val context = BatchContextHolder.context
        val errors = getErrors(context)
        if (ValidateUtils.isNotEmpty(errors)) {
            errors.forEach(Consumer { e: ItemError? ->
                val sourceName = e?.sourceName
                val position = e?.position
                val errorMessage = e?.errorMessage
                logger.error("エラーがあります。ファイル名={}, 行数={}, エラーメッセージ={}", sourceName, position, errorMessage)
            })
            throw ValidationException("取り込むファイルに不正な行が含まれています。")
        }
        return status
    }

    override fun doProcess(context: BatchContext?) {
        val path = Paths.get("src/main/resources/data/stocks.csv")
        val list: MutableList<ImportMstStockDto> = Lists.newArrayList<ImportMstStockDto>()
        try {
            val lines = Files.readAllLines(path, StandardCharsets.UTF_8)
            lines.forEach(Consumer { line: String ->
                val row = StringUtils.csvSplit(line)
                val dto = ImportMstStockDto()
                dto.sourceName = path.toString()
                dto.name = row[0]
                dto.detail = row[1]
                dto.price = row[2]
                dto.imgpath = row[3]
                dto.quantity = row[4]
                list.add(dto)
            })
        } catch (e: IOException) {
            e.printStackTrace()
        }
        val stockList = ObjectMapperUtils.map<Array<Stock>, List<*>>(list, Array<Stock>::class.java)
        for (stock in stockList) {
            stock.createdAt = DateUtils.now
            stock.updatedAt = DateUtils.now
            // TODO DeleteFlgがデフォルト値0のカラムなのに設定しないとエラーになる。。
            // Caused by: java.sql.SQLIntegrityConstraintViolationException: Column 'delete_flg' cannot be null
            stock.deleteFlg = false
            stockDao!!.insert(stock)
        }

        // S3に商品画像をアップロード
        try {
            File("src/main/resources/data/stocks").walk().filter{it -> it.isFile}.forEach  { it -> imageHelper!!.saveFile(it.path, "/stocks", it.name, false) }
        } catch (e: IOException) {
            e.printStackTrace()
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

}
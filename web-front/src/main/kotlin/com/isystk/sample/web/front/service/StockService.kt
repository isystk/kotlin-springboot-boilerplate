package com.isystk.sample.web.front.service

import com.google.common.collect.Lists
import com.isystk.sample.common.dto.Page
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.helper.ImageHelper
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dto.StockRepositoryDto
import com.isystk.sample.domain.repository.StockRepository
import com.isystk.sample.solr.dto.SolrStock
import com.isystk.sample.solr.dto.SolrStockCriteria
import com.isystk.sample.solr.repository.SolrStockRepository
import com.isystk.sample.web.front.dto.StockSearchResultDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.Assert
import java.math.BigInteger
import java.time.format.DateTimeFormatter

@Service
class StockService : BaseTransactionalService() {
    @Autowired
    var solrStockRepository: SolrStockRepository? = null

    @Autowired
    var stockRepository: StockRepository? = null

    @Autowired
    var imageHelper: ImageHelper? = null

    /**
     * Solrの投稿インデックスを取得します。
     *
     * @param criteria
     * @return
     */
    @Transactional(readOnly = true) // 読み取りのみの場合は指定する
    fun findSolrAll(criteria: SolrStockCriteria?, pageable: Pageable): Page<StockSearchResultDto> {
        Assert.notNull(criteria, "criteria must not be null")

        // Solrから商品データを取得する
        val solrStockList: MutableList<StockSearchResultDto> = Lists.newArrayList()
        val count = solrStockRepository!!.count(criteria)
        if (0 < count) {
            val solrStocks = solrStockRepository!!.query(criteria, pageable)
            for (solrStock in solrStocks) {
                solrStockList.add(convertSolrToFrontStockDto(solrStock))
            }
        }
        return pageFactory!!.create(solrStockList, pageable, count)
    }

    /**
     * 商品を取得します。
     *
     * @param stockId
     * @return
     */
    fun findById(stockId: BigInteger): StockSearchResultDto {
        // 1件取得する
        val stock = stockRepository!!.findById(stockId)
        return convertDbToFrontStockDto(stock)
    }

    /**
     * @param solrStock
     * @return
     */
    private fun convertSolrToFrontStockDto(solrStock: SolrStock): StockSearchResultDto {
        // 入力値を詰め替える
        val dto = ObjectMapperUtils.map(solrStock, StockSearchResultDto::class.java)
        dto.imgUrl = imageHelper!!.getImageUrl("/stocks", solrStock.imgpath!!)
        dto.createdAtYYYYMMDD = DateUtils.format(solrStock.createdAtDate!!, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
        dto.createdAtMMDD = DateUtils.format(solrStock.createdAtDate!!, DateTimeFormatter.ofPattern("MM/dd"))
        return dto
    }

    /**
     * @param stockRepositoryDto
     * @return
     */
    private fun convertDbToFrontStockDto(stockRepositoryDto: StockRepositoryDto): StockSearchResultDto {
        // 入力値を詰め替える
        val dto = ObjectMapperUtils.map(stockRepositoryDto, StockSearchResultDto::class.java)
        if (stockRepositoryDto.imgpath !== null) {
            dto.imgUrl = imageHelper!!.getImageUrl("/stocks", stockRepositoryDto.imgpath!!)
        }
        dto.createdAtYYYYMMDD = DateUtils.format(stockRepositoryDto.createdAt, DateTimeFormatter.ofPattern("yyyy/MM/dd"))
        dto.createdAtMMDD = DateUtils.format(stockRepositoryDto.createdAt, DateTimeFormatter.ofPattern("MM/dd"))
        return dto
    }
}
package com.isystk.sample.batch.service

import com.google.common.collect.Lists
import com.isystk.sample.common.service.BaseTransactionalService
import com.isystk.sample.common.util.DateUtils
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dto.StockCriteria
import com.isystk.sample.domain.dto.StockRepositoryDto
import com.isystk.sample.domain.repository.StockRepository
import com.isystk.sample.solr.dto.SolrStock
import com.isystk.sample.solr.repository.SolrStockRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SolrStockService : BaseTransactionalService() {
    @Autowired
    var solrStockRepository: SolrStockRepository? = null

    @Autowired
    var stockRepository: StockRepository? = null

    /**
     * Solrの商品インデックスを更新します。
     *
     * @return
     */
    fun refresh() {

        // 有効な商品を全件取得する
        val criteria = StockCriteria()
        criteria.isDeleteFlgFalse = true
        val stockList = stockRepository!!.findAll(criteria)
        if (stockList.size == 0) {
            // 商品データが0件の場合は何もしない
            return
        }
        val solrStockList: MutableList<SolrStock> = Lists.newArrayList()
        stockList
                .forEach { stock: StockRepositoryDto ->
                    val solrStock = ObjectMapperUtils.map(stock, SolrStock::class.java)
                    solrStock.stockId = stock.id.toInt()
                    solrStock.createdAtDate = DateUtils.toDate(stock.createdAt)
                    solrStockList.add(solrStock)
                }
        try {
            // Solrをすべて削除
            solrStockRepository!!.deleteStockByQuery("*:*")

            // Solrに保存
            solrStockRepository!!.addStocks(solrStockList)
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }
}
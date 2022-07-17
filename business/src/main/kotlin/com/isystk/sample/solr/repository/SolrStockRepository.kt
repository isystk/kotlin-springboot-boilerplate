package com.isystk.sample.solr.repository

import com.isystk.sample.common.dto.Page
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.exception.SystemException
import com.isystk.sample.common.service.BaseRepository
import com.isystk.sample.domain.entity.Stock
import com.isystk.sample.solr.dto.SolrStock
import com.isystk.sample.solr.dto.SolrStockCriteria
import org.apache.solr.client.solrj.SolrClient
import org.apache.solr.client.solrj.SolrQuery
import org.apache.solr.client.solrj.SolrServerException
import org.apache.solr.client.solrj.beans.DocumentObjectBinder
import org.apache.solr.common.SolrDocument
import org.apache.solr.common.SolrDocumentList
import org.apache.solr.common.SolrInputDocument
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Repository
import java.io.IOException

@Repository
class SolrStockRepository : BaseRepository() {
    @Autowired
    var stockSolrClient: SolrClient? = null

    /**
     * インデックスに1レコード追加します。
     *
     * @param stock
     * @throws Exception
     */
    @Throws(Exception::class)
    fun addStocks(stock: SolrStock?) {
        stockSolrClient!!.addBean(stock)
        stockSolrClient!!.optimize()
        stockSolrClient!!.commit()
    }

    /**
     * インデックスに複数レコード追加します。
     *
     * @param stockList
     * @throws Exception
     */
    @Throws(Exception::class)
    fun addStocks(stockList: List<SolrStock?>?) {
        stockSolrClient!!.addBeans(stockList)
        stockSolrClient!!.optimize()
        stockSolrClient!!.commit()
    }

    /**
     * SolrInputDocumentを使用してインデックスに１レコード追加します。
     *
     * @param stock
     * @throws Exception
     */
    @Throws(Exception::class)
    fun addStockBySolrInputDocument(stock: Stock) {
        val solrInputDocument = SolrInputDocument()
        solrInputDocument.addField("id", stock.id)
        solrInputDocument.addField("stock_id", stock.id)
        solrInputDocument.addField("name", stock.name)
        solrInputDocument.addField("detail", stock.detail)
        solrInputDocument.addField("price", stock.price)
        solrInputDocument.addField("quantity", stock.quantity)
        solrInputDocument.addField("imgpath", stock.imgpath)
        solrInputDocument.addField("create_at", stock.createdAt)
        stockSolrClient!!.add(solrInputDocument)
        stockSolrClient!!.optimize()
        stockSolrClient!!.commit()
    }

    /**
     * SolrInputDocumentを使用してインデックスに複数レコード追加します。
     *
     * @param stock
     * @throws Exception
     */
    @Throws(Exception::class)
    fun addStockBySolrInputDocument(stock: List<SolrStock>) {
        val solrInputDocuments: MutableList<SolrInputDocument> = ArrayList()
        for (i in stock.indices) {
            val solrInputDocument = SolrInputDocument()
            solrInputDocument.addField("id", stock[i].id)
            solrInputDocument.addField("stock_id", stock[i].id)
            solrInputDocument.addField("name", stock[i].name)
            solrInputDocument.addField("detail", stock[i].detail)
            solrInputDocument.addField("price", stock[i].price)
            solrInputDocument.addField("quantity", stock[i].quantity)
            solrInputDocument.addField("imgpath", stock[i].imgpath)
            solrInputDocument.addField("create_at", stock[i].createdAtDate)
            solrInputDocuments.add(solrInputDocument)
        }
        stockSolrClient!!.add(solrInputDocuments)
        stockSolrClient!!.optimize()
        stockSolrClient!!.commit()
    }

    /**
     * インデックスを１レコード更新します。
     *
     * @param stock
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateStock(stock: SolrStock) {
        stockSolrClient?.deleteById(stock.id.toString())
        stockSolrClient!!.addBean(stock)
        stockSolrClient!!.optimize()
        stockSolrClient!!.commit()
    }

    /**
     * インデックスを複数レコード更新します。
     *
     * @param stockList
     * @throws Exception
     */
    @Throws(Exception::class)
    fun updateStock(stockList: List<SolrStock?>) {
        val ids = mutableListOf<String>()
        for (i in stockList.indices) {
            ids.add(stockList[i]?.id.toString())
        }
        // 指定IDのレコードを削除
        stockSolrClient!!.deleteById(ids)
        // レコードを追加
        stockSolrClient!!.addBeans(stockList)
        stockSolrClient!!.optimize()
        stockSolrClient!!.commit()
    }

    /**
     * インデックスを１レコード削除します。
     *
     * @param id
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteStock(id: String?) {
        stockSolrClient!!.deleteById(id)
        stockSolrClient!!.optimize()
        stockSolrClient!!.commit()
    }

    /**
     * インデックスを複数レコード削除します。
     *
     * @param ids
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteStock(ids: List<String?>?) {
        stockSolrClient!!.deleteById(ids)
        stockSolrClient!!.optimize()
        stockSolrClient!!.commit()
    }

    /**
     * インデックスを指定したクエリーで削除します。
     *
     * @param queryString
     * @throws Exception
     */
    @Throws(Exception::class)
    fun deleteStockByQuery(queryString: String?) {
        stockSolrClient!!.deleteByQuery(queryString)
        stockSolrClient!!.optimize()
        stockSolrClient!!.commit()
    }

    /**
     * インデックスを指定したクエリーで検索した件数を取得します。
     *
     * @param criteria
     * @return
     * @throws Exception
     */
    fun count(criteria: SolrStockCriteria?): Long {
        return try {
            val stockList: List<SolrStock> = ArrayList()
            val solrQuery = SolrQuery()

            // TODO Criteria を元にQueryを組み立てる
            val queryString = "*:*"
            solrQuery.query = queryString
            solrQuery.start = 0
            solrQuery.rows = Int.MAX_VALUE
            val queryResponse = stockSolrClient!!.query(solrQuery)
            queryResponse.results.numFound
        } catch (e: SolrServerException) {
            throw SystemException(e)
        } catch (e: IOException) {
            throw SystemException(e)
        }
    }

    /**
     * インデックスを指定したクエリーで検索します。
     *
     * @param criteria
     * @param pageable
     * @return
     * @throws Exception
     */
    fun query(criteria: SolrStockCriteria?, pageable: Pageable): List<SolrStock> {
        return try {
            val stockList: MutableList<SolrStock> = ArrayList()
            val solrQuery = SolrQuery()

            // TODO Criteria を元にQueryを組み立てる
            val queryString = "*:*"
            solrQuery.query = queryString
            val start = (pageable.page() - 1) * pageable.perpage()
            solrQuery.start = start
            solrQuery.rows = pageable.perpage()
            solrQuery.highlight = true
            solrQuery.addHighlightField("name")
            solrQuery.addHighlightField("detail")
            solrQuery.highlightFragsize = 200
            solrQuery.highlightSimplePre = "<em>"
            solrQuery.highlightSimplePost = "</em>"
            solrQuery.addSort("score", SolrQuery.ORDER.desc)
            val queryResponse = stockSolrClient!!.query(solrQuery)
            val qtime = queryResponse.qTime //查询花费时间
            val solrDocumentList = queryResponse.results // 获取查询结果集
            // 获取高亮内容 第一个Map的键是文档的ID，第二个Map的键是高亮显示的字段名
            val highlightingMaps = queryResponse.highlighting
            val totals = solrDocumentList.numFound // 查询到的总记录数
            if (!solrDocumentList.isEmpty()) {
                val it: Iterator<SolrDocument> = solrDocumentList.iterator()
                while (it.hasNext()) {
                    val solrDocument = it.next()
                    val id = solrDocument.getFieldValue("id").toString()
                    val highlightFieldMap = highlightingMaps[id]!!
                    if (!highlightFieldMap.isEmpty()) {
                        val highlightTitle = highlightFieldMap["name"]
                        val highlightContent = highlightFieldMap["detail"]
                        if (highlightTitle != null && !highlightTitle.isEmpty()) {
                            val name = highlightTitle[0]
                            solrDocument.setField("name", name)
                        }
                        if (highlightContent != null && !highlightContent.isEmpty()) {
                            val detail = highlightContent[0]
                            solrDocument.setField("detail", detail)
                        }
                    }
                    // 调用solrDocument转java bean
                    val stock = doc2bean(solrDocument)
                    stockList.add(stock)
                }
            }
            stockList
        } catch (e: SolrServerException) {
            throw SystemException(e)
        } catch (e: IOException) {
            throw SystemException(e)
        }
    }

    /**
     * インデックスを指定したクエリーで検索します。
     *
     * @param criteria
     * @param pageable
     * @return
     * @throws Exception
     */
    fun queryPage(criteria: SolrStockCriteria?, pageable: Pageable): Page<SolrStock> {
        var stockList: List<SolrStock> = ArrayList()
        val count = count(criteria)
        if (0 < count) {
            stockList = query(criteria, pageable)
        }
        return pageFactory!!.create(stockList, pageable, count)
    }

    /**
     * SolrDocumentをSolrStockに変換します。
     *
     * @param solrDocument
     * @return
     */
    private fun doc2bean(solrDocument: SolrDocument): SolrStock {
        val binder = DocumentObjectBinder()
        return binder.getBean(SolrStock::class.java, solrDocument)
    }

    /**
     * SolrDocumentのListをSolrStockのListに変換します。
     *
     * @param solrDocumentList
     * @return
     */
    private fun doc2beans(solrDocumentList: SolrDocumentList): List<SolrStock> {
        val binder = DocumentObjectBinder()
        return binder.getBeans(SolrStock::class.java, solrDocumentList)
    }

    /**
     * SolrStockをSolrInputDocumentに変換します。
     *
     * @param stock
     * @return
     */
    private fun bean2doc(stock: SolrStock): SolrInputDocument {
        val binder = DocumentObjectBinder()
        return binder.toSolrInputDocument(stock)
    }
}
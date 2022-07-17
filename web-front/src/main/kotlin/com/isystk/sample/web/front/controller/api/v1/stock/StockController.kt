package com.isystk.sample.web.front.controller.api.v1.stock

import com.google.common.collect.Maps
import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.solr.dto.SolrStockCriteria
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.PageableResource
import com.isystk.sample.web.base.controller.api.resource.PageableResourceImpl
import com.isystk.sample.web.base.controller.api.resource.Resource
import com.isystk.sample.web.front.service.StockService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.math.BigInteger
import java.util.*

@RestController
@RequestMapping(path = [FrontUrl.API_V1_STOCKS], produces = [MediaType.APPLICATION_JSON_VALUE])
class StockController : AbstractRestController() {
    @Autowired
    var stockService: StockService? = null
    override fun getFunctionName(): String {
        return "API_STOCKS"
    }

    /**
     * 商品一覧を複数取得します。
     *
     * @param form
     * @param page
     * @return
     */
    @GetMapping
    fun index(form: StockForm,
              @RequestParam(required = false, defaultValue = "1") page: Int): PageableResource {

        // 入力値からDTOを作成する
        val criteria = ObjectMapperUtils.map(form, SolrStockCriteria::class.java)

        // 10件で区切って取得する
        val stocks = stockService!!.findSolrAll(criteria, form)
        val data = hashMapOf(
                "data" to stocks!!.data,
                "currentPage" to page,
                "total" to stocks.count
        )
        val resource: PageableResource = ObjectMapperUtils.map<PageableResourceImpl, Map<*, *>>(data, PageableResourceImpl::class.java)
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        resource.result = true
        return resource
    }

    /**
     * 商品詳細を取得します。
     *
     * @param stockId
     * @return
     */
    @GetMapping(value = ["/{stockId}"])
    fun show(@PathVariable stockId: BigInteger): Resource {
        val resource = resourceFactory!!.create()
        resource.data = Arrays.asList(stockService!!.findById(stockId))
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        resource.result = true
        return resource
    }
}
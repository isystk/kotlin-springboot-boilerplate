package com.isystk.sample.web.admin.controller.html.stock

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.web.admin.service.StockService
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import java.math.BigInteger

@Controller
@RequestMapping(AdminUrl.STOCKS)
class StockDetailController : AbstractHtmlController() {
    @Autowired
    var stockService: StockService? = null
    override fun getFunctionName(): String {
        return "A_STOCK_DETAIL"
    }

    /**
     * 詳細画面表示
     *
     * @param stockId
     * @param model
     * @return
     */
    @GetMapping("{stockId}")
    fun show(@PathVariable stockId: BigInteger, model: Model): String {
        val stock = stockService!!.findById(stockId)
        model.addAttribute("stock", stock)
        return "modules/stock/detail"
    }

    companion object {
        private val log = LoggerFactory.getLogger(StockDetailController::class.java)
    }
}
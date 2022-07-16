package com.isystk.sample.web.admin.controller.html.order

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.web.admin.service.OrderHistoryService
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
@RequestMapping(AdminUrl.ORDERS)
class OrderDetailController : AbstractHtmlController() {
    @Autowired
    var orderHistoryService: OrderHistoryService? = null
    override fun getFunctionName(): String {
        return "A_ORDER_DETAIL"
    }

    /**
     * 詳細画面表示
     *
     * @param orderId
     * @param model
     * @return
     */
    @GetMapping("{orderId}")
    fun show(@PathVariable orderId: BigInteger?, model: Model): String {
        val order = orderHistoryService!!.findById(orderId)
        model.addAttribute("order", order)
        return "modules/order/detail"
    }

    companion object {
        private val log = LoggerFactory.getLogger(OrderDetailController::class.java)
    }
}
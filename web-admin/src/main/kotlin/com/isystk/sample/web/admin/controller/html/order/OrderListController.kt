package com.isystk.sample.web.admin.controller.html.order

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.web.admin.dto.OrderHistorySearchConditionDto
import com.isystk.sample.web.admin.service.OrderHistoryService
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
@RequestMapping(AdminUrl.ORDERS)
class OrderListController : AbstractHtmlController() {
    @Autowired
    var orderHistoryService: OrderHistoryService? = null

    @Autowired
    var orderListFormValidator: OrderListFormValidator? = null
    override fun getFunctionName(): String {
        return "A_ORDER_LIST"
    }

    @ModelAttribute
    fun initForm(): OrderListForm {
        return OrderListForm()
    }

    @InitBinder
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(orderListFormValidator)
    }

    /**
     * 一覧画面表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping
    fun index(@ModelAttribute form: @Valid OrderListForm?, br: BindingResult,
              sessionStatus: SessionStatus?, attributes: RedirectAttributes?, model: Model): String {
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return "modules/order/list"
        }

        // 10件区切りで取得する
        val pages = orderHistoryService!!.findPage(formToDto(form), form)

        // 画面に検索結果を渡す
        model.addAttribute("pages", pages)
        return "modules/order/list"
    }

    /**
     * 検索条件を詰める
     *
     * @return
     */
    private fun formToDto(
            form: OrderListForm?): OrderHistorySearchConditionDto {

        // 入力値を詰め替える
        return ObjectMapperUtils.map(form, OrderHistorySearchConditionDto::class.java)
    }

    companion object {
        private val log = LoggerFactory.getLogger(OrderListController::class.java)
    }
}
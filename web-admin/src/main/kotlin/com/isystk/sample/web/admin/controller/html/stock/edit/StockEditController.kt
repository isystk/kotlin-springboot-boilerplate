package com.isystk.sample.web.admin.controller.html.stock.edit

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dto.StockRepositoryDto
import com.isystk.sample.web.admin.service.StockService
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.servlet.mvc.support.RedirectAttributes

@Controller
@RequestMapping(AdminUrl.STOCKS_EDIT)
@SessionAttributes(types = [StockEditForm::class])
class StockEditController : AbstractHtmlController() {
    @Autowired
    var stockService: StockService? = null

    @Autowired
    var stockEditFormValidator: StockEditFormValidator? = null
    @ModelAttribute
    fun initForm(): StockEditForm {
        return StockEditForm()
    }

    @InitBinder
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(stockEditFormValidator)
    }

    override fun getFunctionName(): String {
        return "A_STOCK_EDIT"
    }

    /**
     * 初期表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping("{stockId}")
    fun editIndex(@ModelAttribute form: StockEditForm, model: Model): String {

        // 1件取得する
        val stock = stockService!!.findById(form.stockId)

        // 取得したDtoをFromに詰め替える
        ObjectMapperUtils.map(stock, form)
        return showEditIndex(form, model)
    }

    /**
     * 修正画面表示
     *
     * @param form
     * @param model
     * @return
     */
    private fun showEditIndex(
            form: StockEditForm, model: Model): String {
        return "modules/stock/edit/index"
    }

    /**
     * 修正確認画面表示
     *
     * @param form
     * @param br
     * @param sessionStatus
     * @param attributes
     * @return
     */
    @PutMapping(params = ["confirm"])
    fun editConfirm(@Validated @ModelAttribute form: StockEditForm, br: BindingResult,
                    sessionStatus: SessionStatus?, attributes: RedirectAttributes?, model: Model): String {

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return showEditIndex(form, model)
        }
        return "modules/stock/edit/confirm"
    }

    /**
     * 前に戻る
     *
     * @param form
     * @param br
     * @param sessionStatus
     * @param attributes
     * @return
     */
    @PutMapping(params = ["back"])
    fun editBack(@Validated @ModelAttribute form: StockEditForm, br: BindingResult?,
                 sessionStatus: SessionStatus?, attributes: RedirectAttributes?, model: Model): String {
        return showEditIndex(form, model)
    }

    /**
     * 更新処理
     *
     * @param form
     * @param br
     * @param sessionStatus
     * @param attributes
     * @return
     */
    @PutMapping(params = ["complete"])
    fun updateComplete(@Validated @ModelAttribute form: StockEditForm, br: BindingResult,
                       sessionStatus: SessionStatus, attributes: RedirectAttributes?, model: Model): String {

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return showEditIndex(form, model)
        }

        // 入力値を詰め替える
        val tStocksDto = ObjectMapperUtils.map(form, StockRepositoryDto::class.java)
        tStocksDto.id = form.stockId

        // 更新する
        stockService!!.update(tStocksDto)

        // セッションのstocksEditFormをクリアする
        sessionStatus.setComplete()
        return "redirect:/stocks/edit/complete"
    }

    /**
     * 修正完了画面表示
     *
     * @return
     */
    @GetMapping("complete")
    fun showComplete(): String {
        return "modules/stock/edit/complete"
    }

    companion object {
        private val log = LoggerFactory.getLogger(StockEditController::class.java)
    }
}
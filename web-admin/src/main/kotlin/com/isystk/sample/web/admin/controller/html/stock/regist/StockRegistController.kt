package com.isystk.sample.web.admin.controller.html.stock.regist

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dto.StockRepositoryDto
import com.isystk.sample.web.admin.service.StockService
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
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
@RequestMapping(AdminUrl.STOCKS_REGIST)
@SessionAttributes(types = [StockRegistForm::class])
class StockRegistController : AbstractHtmlController() {
    @Autowired
    var stockService: StockService? = null

    @Autowired
    var stockRegistFormValidator: StockRegistFormValidator? = null
    @ModelAttribute
    fun initForm(): StockRegistForm {
        return StockRegistForm()
    }

    @InitBinder
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(stockRegistFormValidator)
    }

    override fun getFunctionName(): String {
        return "A_STOCK_REGIST"
    }

    /**
     * 初期表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping
    fun registIndex(@ModelAttribute form: StockRegistForm, model: Model): String {

        // SessionAttributeを再生成する
        model.addAttribute("stocksRegistForm", StockRegistForm())
        return showRegistIndex(form, model)
    }

    /**
     * 登録画面表示
     *
     * @param form
     * @param model
     * @return
     */
    private fun showRegistIndex(
            form: StockRegistForm, model: Model): String {
        return "modules/stock/regist/index"
    }

    /**
     * 登録確認画面表示
     *
     * @param form
     * @param br
     * @param sessionStatus
     * @param attributes
     * @return
     */
    @PostMapping(params = ["confirm"])
    fun registConfirm(@Validated @ModelAttribute form: StockRegistForm, br: BindingResult,
                      sessionStatus: SessionStatus?, attributes: RedirectAttributes, model: Model): String {

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return showRegistIndex(form, model)
        }
        return "modules/stock/regist/confirm"
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
    @PostMapping(params = ["back"])
    fun registBack(@Validated @ModelAttribute form: StockRegistForm, br: BindingResult?,
                   sessionStatus: SessionStatus?, attributes: RedirectAttributes, model: Model): String {
        return showRegistIndex(form, model)
    }

    /**
     * 登録処理
     *
     * @param form
     * @param br
     * @param sessionStatus
     * @param attributes
     * @return
     */
    @PostMapping(params = ["complete"])
    fun registComplete(@Validated @ModelAttribute form: StockRegistForm, br: BindingResult,
                       sessionStatus: SessionStatus, attributes: RedirectAttributes, model: Model): String {

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return showRegistIndex(form, model)
        }

        // 入力値を詰め替える
        val tStocksDto = ObjectMapperUtils.map(form, StockRepositoryDto::class.java)

        // 登録する
        stockService!!.create(tStocksDto)

        // セッションのstocksRegistFormをクリアする
        sessionStatus.setComplete()
        return "redirect:/stocks/regist/complete"
    }

    /**
     * 登録完了画面表示
     *
     * @return
     */
    @GetMapping("complete")
    fun showComplete(): String {
        return "modules/stock/regist/complete"
    }

}
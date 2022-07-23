package com.isystk.sample.web.admin.controller.html.stock

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.web.admin.dto.StockSearchConditionDto
import com.isystk.sample.web.admin.service.StockService
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import com.isystk.sample.web.base.view.CsvView
import com.isystk.sample.web.base.view.ExcelView
import com.isystk.sample.web.base.view.PdfView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import javax.validation.Valid

@Controller
@RequestMapping(AdminUrl.STOCKS)
class StockListController : AbstractHtmlController() {
    @Autowired
    var stockService: StockService? = null

    @Autowired
    var stockListFormValidator: StockListFormValidator? = null
    override fun getFunctionName(): String {
        return "A_STOCK_LIST"
    }

    @ModelAttribute
    fun initForm(): StockListForm {
        return StockListForm()
    }

    @InitBinder
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(stockListFormValidator)
    }

    /**
     * 一覧画面表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping
    fun index(@ModelAttribute form: @Valid StockListForm, br: BindingResult,
              sessionStatus: SessionStatus?, attributes: RedirectAttributes, model: Model): String {
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return "modules/stock/list"
        }

        // 10件区切りで取得する
        val pages = stockService!!.findPage(formToDto(form), form)

        // 画面に検索結果を渡す
        model.addAttribute("pages", pages)
        return "modules/stock/list"
    }

    /**
     * 検索条件を詰める
     *
     * @return
     */
    private fun formToDto(
            form: StockListForm?): StockSearchConditionDto {

        // 入力値を詰め替える
        return ObjectMapperUtils.map(form, StockSearchConditionDto::class.java)
    }

    /**
     * 削除処理
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Long): String {
        stockService!!.delete(id)
        return "redirect:/stocks"
    }

    /**
     * CSVダウンロード
     *
     * @param filename
     * @return
     */
    @GetMapping("/download/{filename:.+\\.csv}")
    fun downloadCsv(@PathVariable filename: String, form: StockListForm?, model: Model?): CsvView {

        // 全件取得する
        val stocks = stockService!!.findAll(formToDto(form))

        // 詰め替える
        val csvList = ObjectMapperUtils.mapAll(stocks, StockCsv::class.java)

        // CSVスキーマクラス、データ、ダウンロード時のファイル名を指定する
        return CsvView(StockCsv::class.java, csvList, filename)
    }

    /**
     * Excelダウンロード
     *
     * @param filename
     * @return
     */
    @GetMapping(path = ["/download/{filename:.+\\.xlsx}"])
    fun downloadExcel(@PathVariable filename: String?, form: StockListForm?,
                      model: Model?): ModelAndView {

        // 全件取得する
        val stocks = stockService!!.findAll(formToDto(form))

        // Excelプック生成コールバック、データ、ダウンロード時のファイル名を指定する
        val view = ExcelView(StockExcel(), stocks, filename)
        return ModelAndView(view)
    }

    /**
     * PDFダウンロード
     *
     * @param filename
     * @return
     */
    @GetMapping(path = ["/download/{filename:.+\\.pdf}"])
    fun downloadPdf(@PathVariable filename: String, form: StockListForm?, model: Model?): ModelAndView {

        // 全件取得する
        val stocks = stockService!!.findAll(formToDto(form))

        // 帳票レイアウト、データ、ダウンロード時のファイル名を指定する
        val view = PdfView("reports/stocks.jrxml", stocks, filename)
        return ModelAndView(view)
    }

}
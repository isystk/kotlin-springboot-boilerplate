package com.isystk.sample.web.admin.controller.html.post

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.common.dto.Pageable
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.dto.TPostCriteria
import com.isystk.sample.domain.repository.MPostTagRepository
import com.isystk.sample.domain.repository.TPostRepository
import com.isystk.sample.web.admin.service.PostService
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import com.isystk.sample.web.base.view.CsvView
import com.isystk.sample.web.base.view.ExcelView
import com.isystk.sample.web.base.view.PdfView
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import org.springframework.web.bind.support.SessionStatus
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.mvc.support.RedirectAttributes
import java.time.LocalTime
import javax.validation.Valid

@Controller
@RequestMapping(AdminUrl.POST)
@SessionAttributes(types = [PostSearchForm::class])
class PostHtmlController : AbstractHtmlController() {
    @Autowired
    var postService: PostService? = null

    @Autowired
    var postRepository: TPostRepository? = null

    @Autowired
    var postSearchFormValidator: PostFormValidator? = null

    @Autowired
    var userHelper: UserHelper? = null

    @Autowired
    var mPostTagRepository: MPostTagRepository? = null
    override fun getFunctionName(): String {
        return "A_POST"
    }

    @ModelAttribute("postSearchForm")
    fun postSearchForm(): PostSearchForm {
        return PostSearchForm()
    }

    @InitBinder("postSearchForm")
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(postSearchFormValidator)
    }

    /**
     * 一覧画面表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping
    fun index(@ModelAttribute form: @Valid PostSearchForm?, br: BindingResult,
              sessionStatus: SessionStatus?, attributes: RedirectAttributes?, model: Model): String {
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return "modules/post/list"
        }

        // 10件区切りで取得する
        val pages = postRepository!!.findAll(formToCriteria(form), form)

        // 画面に検索結果を渡す
        model.addAttribute("pages", pages)
        return "modules/post/list"
    }

    /**
     * 検索条件を詰める
     *
     * @return
     */
    private fun formToCriteria(form: PostSearchForm?): TPostCriteria {

        // 入力値を詰め替える
        val criteria = TPostCriteria()
        criteria.postIdEq = form!!.postId
        criteria.userIdEq = form!!.userId
        criteria.titleLike = form!!.title
        if (form.registDateFrom != null) {
            criteria.registTimeGe = form!!.registDateFrom!!.atStartOfDay()
        }
        if (form.registDateTo != null) {
            criteria.registTimeLe = form!!.registDateTo!!.atTime(LocalTime.MAX)
        }
        criteria.isDeleteFlgFalse = true
        criteria.orderBy = "order by update_time desc"
        return criteria
    }

    /**
     * 詳細画面表示
     *
     * @param postId
     * @param model
     * @return
     */
    @GetMapping("{postId}")
    fun show(@PathVariable postId: Int?, model: Model): String {
        val post = postRepository!!.findById(postId)
        model.addAttribute("post", post)
        val tUser = userHelper!!.getUser(post.userId)
        model.addAttribute("userName", java.lang.String.join(tUser.familyName, " ", tUser.name))

        // タグの一覧
        model.addAttribute("postTagList", mPostTagRepository!!.findAllSelectList())
        return "modules/post/detail"
    }

    /**
     * 削除処理
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Int?): String {
        postService!!.delete(id)
        return "redirect:/post"
    }

    /**
     * CSVダウンロード
     *
     * @param filename
     * @return
     */
    @GetMapping("/download/{filename:.+\\.csv}")
    fun downloadCsv(@PathVariable filename: String?, form: PostSearchForm, model: Model?): CsvView {

        // 全件取得する
        form.perpage = Pageable.NO_LIMIT.perpage
        val pages = postRepository!!.findAll(formToCriteria(form), form)

        // 詰め替える
        val csvList = ObjectMapperUtils.mapAll(pages.data, PostCsv::class.java)

        // CSVスキーマクラス、データ、ダウンロード時のファイル名を指定する
        return CsvView(PostCsv::class.java, csvList, filename)
    }

    /**
     * Excelダウンロード
     *
     * @param filename
     * @return
     */
    @GetMapping(path = ["/download/{filename:.+\\.xlsx}"])
    fun downloadExcel(@PathVariable filename: String?, form: PostSearchForm,
                      model: Model?): ModelAndView {

        // 全件取得する
        form.perpage = Pageable.NO_LIMIT.perpage
        val pages = postRepository!!.findAll(formToCriteria(form), form)

        // Excelプック生成コールバック、データ、ダウンロード時のファイル名を指定する
        val view = ExcelView(PostExcel(), pages.data, filename)
        return ModelAndView(view)
    }

    /**
     * PDFダウンロード
     *
     * @param filename
     * @return
     */
    @GetMapping(path = ["/download/{filename:.+\\.pdf}"])
    fun downloadPdf(@PathVariable filename: String?, form: PostSearchForm, model: Model?): ModelAndView {

        // 全件取得する
        form.perpage = Pageable.NO_LIMIT.perpage
        val pages = postRepository!!.findAll(formToCriteria(form), form)

        // 帳票レイアウト、データ、ダウンロード時のファイル名を指定する
        val view = PdfView("reports/post.jrxml", pages.data, filename)
        return ModelAndView(view)
    }

    companion object {
        private val log = LoggerFactory.getLogger(PostHtmlController::class.java)
    }
}
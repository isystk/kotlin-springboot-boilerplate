package com.isystk.sample.batch.controller.html.post.tag

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.domain.dto.MPostTagCriteria
import com.isystk.sample.domain.repository.MPostTagRepository
import com.isystk.sample.domain.repository.dto.MPostTagRepositoryDto
import com.isystk.sample.web.base.controller.html.AbstractHtmlController
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*
import javax.validation.constraints.NotBlank

@Controller
@RequestMapping(AdminUrl.POST_TAG)
@SessionAttributes(types = [PostTagSearchForm::class])
class PostTagHtmlController : AbstractHtmlController() {
    @Autowired
    var mPostTagRepository: MPostTagRepository? = null
    override fun getFunctionName(): String {
        return "A_POST_TAG"
    }

    /**
     * 一覧画面表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping
    fun index(form: PostTagSearchForm, model: Model): String {

        // 10件区切りで取得する
        val pages = mPostTagRepository!!.find(formToCriteria(form), form)

        // 画面に検索結果を渡す
        model.addAttribute("pages", pages)
        return "modules/post/tag/list"
    }

    /**
     * 検索条件を詰める
     *
     * @return
     */
    private fun formToCriteria(form: PostTagSearchForm): MPostTagCriteria {

        // 入力値を詰め替える
        val criteria = MPostTagCriteria()
        criteria.nameLike = form.postTagName
        criteria.isDeleteFlgFalse = true
        criteria.orderBy = "order by regist_time desc, post_tag_id asc"
        return criteria
    }

    /**
     * 新規登録処理
     *
     * @param name
     * @return
     */
    @PostMapping
    fun create(@RequestParam("name") name: @NotBlank String?): String {

        // 入力値を詰め替える
        val dto = MPostTagRepositoryDto()
        dto.name = name
        // 登録する
        mPostTagRepository!!.create(dto)
        return "redirect:/post/tag"
    }

    /**
     * 削除処理
     *
     * @param id
     * @return
     */
    @DeleteMapping("{id}")
    fun delete(@PathVariable id: Int?): String {

        // 削除する
        mPostTagRepository!!.delete(id)
        return "redirect:/post/tag"
    }

    companion object {
        private val log = LoggerFactory.getLogger(PostTagHtmlController::class.java)
    }
}
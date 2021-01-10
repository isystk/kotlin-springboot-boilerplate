package com.isystk.sample.batch.controller.html.post.regist

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.common.dto.CodeValueDto
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.entity.TPostImage
import com.isystk.sample.domain.entity.TPostTag
import com.isystk.sample.domain.entity.TUser
import com.isystk.sample.domain.repository.MPostTagRepository
import com.isystk.sample.domain.repository.dto.TPostRepositoryDto
import com.isystk.sample.web.batch.service.PostService
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
import java.util.*
import java.util.stream.Collectors

@Controller
@RequestMapping(AdminUrl.POST_REGIST)
@SessionAttributes(types = [PostRegistForm::class])
class PostRegistHtmlController : AbstractHtmlController() {
    @Autowired
    var postService: PostService? = null

    @Autowired
    var postRigstFormValidator: PostRigstFormValidator? = null

    @Autowired
    var userHelper: UserHelper? = null

    @Autowired
    var mPostTagRepository: MPostTagRepository? = null

    @ModelAttribute("postRegistForm")
    fun postRegistForm(): PostRegistForm {
        return PostRegistForm()
    }

    @InitBinder("postRegistForm")
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(postRigstFormValidator)
    }

    override fun getFunctionName(): String {
        return "A_POST_REGIST"
    }

    /**
     * 初期表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping
    fun registIndex(@ModelAttribute form: PostRegistForm?, model: Model): String {

        // SessionAttributeを再生成する
        model.addAttribute("postRegistForm", PostRegistForm())
        return showRegistIndex(model)
    }

    /**
     * 登録画面表示
     *
     * @param model
     * @return
     */
    private fun showRegistIndex(model: Model): String {
        // ユーザー一覧
        model.addAttribute("userList", userHelper!!.userList
                .stream()
                .map { tUser: TUser ->
                    val dto = CodeValueDto()
                    dto.code = tUser.userId
                    dto.text = java.lang.String.join(tUser.familyName, " ", tUser.name)
                    dto
                }.collect(Collectors.toList())
        )

        // タグの一覧
        model.addAttribute("postTagList", mPostTagRepository!!.findAllSelectList())
        return "modules/post/regist/index"
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
    fun registConfirm(@Validated @ModelAttribute form: PostRegistForm, br: BindingResult,
                      sessionStatus: SessionStatus?, attributes: RedirectAttributes?, model: Model): String {
        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return showRegistIndex(model)
        }
        val tUser = userHelper!!.getUser(form.userId)
        model.addAttribute("userName", java.lang.String.join(tUser.familyName, " ", tUser.name))

        // タグの一覧
        model.addAttribute("postTagList", mPostTagRepository!!.findAllSelectList())
        return "modules/post/regist/confirm"
    }

    /**
     * 前に戻る
     *
     * @param form
     * @param model
     * @return
     */
    @PostMapping(params = ["back"])
    fun registBack(@Validated @ModelAttribute form: PostRegistForm?, br: BindingResult?,
                   sessionStatus: SessionStatus?, attributes: RedirectAttributes?, model: Model): String {
        return showRegistIndex(model)
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
    fun registComplete(@Validated @ModelAttribute form: PostRegistForm, br: BindingResult,
                       sessionStatus: SessionStatus?, attributes: RedirectAttributes?, model: Model): String {
        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return showRegistIndex(model)
        }

        // 入力値からDTOを作成する
        val tPostDto = ObjectMapperUtils.map(form, TPostRepositoryDto::class.java)
        // 投稿画像
        tPostDto.tPostImageList = Optional.ofNullable<List<Int?>?>(form.postImageId)
                .map { list: List<Int?>? ->
                    list!!.stream()
                            .map { imageId: Int? ->
                                val tPostImage = TPostImage()
                                tPostImage.imageId = imageId
                                tPostImage
                            }
                            .collect(Collectors.toList())
                }
                .orElse(null)
        // 投稿タグ
        tPostDto.tPostTagList = Optional.ofNullable<List<Int?>?>(form.postTagId)
                .map { list: List<Int?>? ->
                    list!!.stream()
                            .map { tagId: Int? ->
                                val tPostTag = TPostTag()
                                tPostTag.postTagId = tagId
                                tPostTag
                            }
                            .collect(Collectors.toList())
                }
                .orElse(null)
        val postId = postService!!.create(tPostDto)
        return "redirect:/post/regist/complete"
    }

    /**
     * 登録完了画面表示
     *
     * @return
     */
    @GetMapping("complete")
    fun showComplete(): String {
        return "modules/post/regist/complete"
    }

    companion object {
        private val log = LoggerFactory
                .getLogger(PostRegistHtmlController::class.java)
    }
}
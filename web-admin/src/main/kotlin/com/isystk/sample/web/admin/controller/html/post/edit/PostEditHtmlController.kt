package com.isystk.sample.web.admin.controller.html.post.edit

import com.isystk.sample.common.AdminUrl
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.entity.TPostImage
import com.isystk.sample.domain.entity.TPostTag
import com.isystk.sample.domain.repository.MPostTagRepository
import com.isystk.sample.domain.repository.TPostRepository
import com.isystk.sample.domain.repository.dto.TPostRepositoryDto
import com.isystk.sample.web.admin.service.PostService
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
import javax.servlet.http.HttpSession

@Controller
@RequestMapping(AdminUrl.POST_EDIT)
@SessionAttributes(types = [PostEditForm::class])
class PostEditHtmlController : AbstractHtmlController() {
    @Autowired
    var postService: PostService? = null

    @Autowired
    var postRepository: TPostRepository? = null

    @Autowired
    var userHelper: UserHelper? = null

    @Autowired
    var postEditFormValidator: PostEditFormValidator? = null

    @Autowired
    var mPostTagRepository: MPostTagRepository? = null

    @Autowired
    var session: HttpSession? = null

    @ModelAttribute("postEditForm")
    fun postEditForm(): PostEditForm {
        return PostEditForm()
    }

    @InitBinder("postEditForm")
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(postEditFormValidator)
    }

    override fun getFunctionName(): String {
        return "A_POST_EDIT"
    }

    /**
     * 初期表示
     *
     * @param form
     * @param model
     * @return
     */
    @GetMapping("{postId}")
    fun editIndex(@ModelAttribute form: PostEditForm, model: Model): String {
//
//        // SessionAttributeを再生成する
//        model.addAttribute("postEditForm", new PostEditForm());

        // 1件取得する
        val post = postRepository!!.findById(form.postId)

        // 取得したDtoをFromに詰め替える
        ObjectMapperUtils.map(post, form)
        // 投稿画像
        form.postImageId =
                Optional.ofNullable(post.tPostImageList)
                        .map { list: List<TPostImage> ->
                            list.stream()
                                    .map { s: TPostImage -> s.imageId }
                                    .collect(Collectors.toList())
                        }
                        .orElse(null)
        // 投稿タグ
        form.postTagId =
                Optional.ofNullable(post.tPostTagList)
                        .map { list: List<TPostTag> ->
                            list.stream()
                                    .map { s: TPostTag -> s.postTagId }
                                    .collect(Collectors.toList())
                        }
                        .orElse(null)
        return showEditIndex(form, model)
    }

    /**
     * 修正画面表示
     *
     * @param form
     * @param model
     * @return
     */
    private fun showEditIndex(form: PostEditForm, model: Model): String {
        val tUser = userHelper!!.getUser(form.userId)
        model.addAttribute("userName", java.lang.String.join(tUser.familyName, " ", tUser.name))

        // タグの一覧
        model.addAttribute("postTagList", mPostTagRepository!!.findAllSelectList())
        return "modules/post/edit/index"
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
    fun editConfirm(@Validated @ModelAttribute form: PostEditForm, br: BindingResult,
                    sessionStatus: SessionStatus?, attributes: RedirectAttributes?, model: Model): String {

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return showEditIndex(form, model)
        }
        val tUser = userHelper!!.getUser(form.userId)
        model.addAttribute("userName", java.lang.String.join(tUser.familyName, " ", tUser.name))

        // タグの一覧
        model.addAttribute("postTagList", mPostTagRepository!!.findAllSelectList())
        return "modules/post/edit/confirm"
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
    fun editBack(@Validated @ModelAttribute form: PostEditForm, br: BindingResult?,
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
    fun updateComplete(@Validated @ModelAttribute form: PostEditForm, br: BindingResult,
                       sessionStatus: SessionStatus, attributes: RedirectAttributes?, model: Model): String {

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            setFlashAttributeErrors(attributes, br)
            return showEditIndex(form, model)
        }

        // 入力値を詰め替える
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
        // 更新する
        postService!!.update(tPostDto)

        // セッションのpostEditFormをクリアする
        sessionStatus.setComplete()
        return "redirect:/post/edit/complete"
    }

    /**
     * 修正完了画面表示
     *
     * @return
     */
    @GetMapping("complete")
    fun showComplete(): String {
        return "modules/post/edit/complete"
    }

    companion object {
        private val log = LoggerFactory.getLogger(PostEditHtmlController::class.java)
    }
}
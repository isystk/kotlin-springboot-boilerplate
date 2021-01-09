package com.isystk.sample.web.front.controller.api.v1.member.post

import com.isystk.sample.common.Const
import com.isystk.sample.common.FrontUrl
import com.isystk.sample.common.exception.ValidationErrorException
import com.isystk.sample.common.helper.UserHelper
import com.isystk.sample.common.util.ObjectMapperUtils
import com.isystk.sample.domain.entity.TPostImage
import com.isystk.sample.domain.entity.TPostTag
import com.isystk.sample.domain.repository.dto.TPostRepositoryDto
import com.isystk.sample.web.base.controller.api.AbstractRestController
import com.isystk.sample.web.base.controller.api.resource.Resource
import com.isystk.sample.web.front.dto.FrontPostDto
import com.isystk.sample.web.front.service.PostService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.Errors
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@SessionAttributes(types = [MemberPostRegistRestForm::class])
class MemberPostDetailRestController : AbstractRestController() {
    @Autowired
    var postService: PostService? = null

    @Autowired
    var userHelper: UserHelper? = null

    @Autowired
    var memberPostEditRestValidator: MemberPostEditRestValidator? = null

    @ModelAttribute("memberPostRegistRestForm")
    fun memberPostRegistRestForm(): MemberPostRegistRestForm {
        return MemberPostRegistRestForm()
    }

    @ModelAttribute("memberPostEditRestForm")
    fun memberPostEditRestForm(): MemberPostEditRestForm {
        return MemberPostEditRestForm()
    }

    @InitBinder("memberPostEditRestForm")
    fun validatorBinder(binder: WebDataBinder) {
        binder.addValidators(memberPostEditRestValidator)
    }

    override fun getFunctionName(): String {
        return "API_MEMBER_POSTS_DETAIL"
    }

    /**
     * 指定した投稿IDに紐づく自分の投稿データを取得します。
     *
     * @param postId
     * @param model
     * @return
     */
    @GetMapping(FrontUrl.API_V1_MEMBER_POSTS_DETAIL)
    fun showDetail(@PathVariable postId: Int?, model: Model?): Resource {

        // 1件取得する
        val post = postService!!.findMyDataById(postId)
        val resource = resourceFactory.create()
        resource.data = Arrays.asList(post!!.orElse(FrontPostDto()))
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }

    /**
     * 更新処理
     *
     * @param form
     * @param br
     * @param errors
     * @return
     */
    @PutMapping(FrontUrl.API_V1_MEMBER_POSTS_EDIT)
    fun update(
            @Validated @ModelAttribute("memberPostEditRestForm") form: MemberPostEditRestForm,
            br: BindingResult, errors: Errors?): Resource {
        val resource = resourceFactory.create()

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            throw ValidationErrorException(errors)
        }

        // 入力値を詰め替える
        val tPostDto = ObjectMapperUtils.map(form, TPostRepositoryDto::class.java)
        // 投稿画像
        tPostDto.tPostImageList = Optional.ofNullable(form.imageList)
                .map { list: List<MemberPostDetailImageRestForm> ->
                    list.stream()
                            .map { image: MemberPostDetailImageRestForm ->
                                val tPostImage = TPostImage()
                                tPostImage.imageId = image.imageId
                                tPostImage
                            }
                            .collect(Collectors.toList())
                }
                .orElse(null)
        // 投稿タグ
        tPostDto.tPostTagList = Optional.ofNullable(form.tagList)
                .map { list: List<MemberPostDetailTagRestForm> ->
                    list.stream()
                            .map { tag: MemberPostDetailTagRestForm ->
                                val tPostTag = TPostTag()
                                tPostTag.postTagId = tag.tagId
                                tPostTag
                            }
                            .collect(Collectors.toList())
                }
                .orElse(null)
        // 更新する
        postService!!.update(tPostDto)

        // 1件取得する
        val post = postService!!.findMyDataById(form.postId)
        resource.data = Arrays.asList(post!!.orElse(FrontPostDto()))
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }

    /**
     * 登録処理
     *
     * @param form
     * @param br
     * @param errors
     * @return
     */
    @PostMapping(FrontUrl.API_V1_MEMBER_POSTS_NEW)
    fun regist(
            @Validated @ModelAttribute("memberPostRegistRestForm") form: MemberPostRegistRestForm,
            br: BindingResult, errors: Errors?): Resource {
        val resource = resourceFactory.create()

        // 入力チェックエラーがある場合は、元の画面にもどる
        if (br.hasErrors()) {
            throw ValidationErrorException(errors)
        }

        // 入力値からDTOを作成する
        val tPostDto = ObjectMapperUtils.map(form, TPostRepositoryDto::class.java)
        // ログインユーザーID
        tPostDto.userId = userHelper!!.loginUserId
        // 投稿画像
        tPostDto.tPostImageList = Optional.ofNullable(form.imageList)
                .map { list: List<MemberPostDetailImageRestForm> ->
                    list.stream()
                            .map { image: MemberPostDetailImageRestForm ->
                                val tPostImage = TPostImage()
                                tPostImage.imageId = image.imageId
                                tPostImage
                            }
                            .collect(Collectors.toList())
                }
                .orElse(null)
        // 投稿タグ
        tPostDto.tPostTagList = Optional.ofNullable(form.tagList)
                .map { list: List<MemberPostDetailTagRestForm> ->
                    list.stream()
                            .map { tag: MemberPostDetailTagRestForm ->
                                val tPostTag = TPostTag()
                                tPostTag.postTagId = tag.tagId
                                tPostTag
                            }
                            .collect(Collectors.toList())
                }
                .orElse(null)
        val postId = postService!!.create(tPostDto)

        // 1件取得する
        val post = postService!!.findMyDataById(postId)
        resource.data = Arrays.asList(post!!.orElse(FrontPostDto()))
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }

    /**
     * 削除処理
     *
     * @param postId
     * @return
     */
    @DeleteMapping(FrontUrl.API_V1_MEMBER_POSTS_DELETE)
    fun delete(@PathVariable postId: Int?): Resource {
        postService!!.delete(postId)
        val resource = resourceFactory.create()
        resource.message = getMessage(Const.MESSAGE_SUCCESS)
        return resource
    }

    companion object {
        private val log = LoggerFactory
                .getLogger(MemberPostDetailRestController::class.java)
    }
}
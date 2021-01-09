package com.isystk.sample.web.front.controller.api.v1.member.post;

import static com.isystk.sample.common.Const.MESSAGE_SUCCESS;
import static com.isystk.sample.common.FrontUrl.API_V1_MEMBER_POSTS_DELETE;
import static com.isystk.sample.common.FrontUrl.API_V1_MEMBER_POSTS_DETAIL;
import static com.isystk.sample.common.FrontUrl.API_V1_MEMBER_POSTS_EDIT;
import static com.isystk.sample.common.FrontUrl.API_V1_MEMBER_POSTS_NEW;

import com.isystk.sample.common.exception.ValidationErrorException;
import com.isystk.sample.common.helper.UserHelper;
import com.isystk.sample.common.util.ObjectMapperUtils;
import com.isystk.sample.domain.entity.TPostImage;
import com.isystk.sample.domain.entity.TPostTag;
import com.isystk.sample.domain.repository.dto.TPostRepositoryDto;
import com.isystk.sample.web.base.controller.api.AbstractRestController;
import com.isystk.sample.web.base.controller.api.resource.Resource;
import com.isystk.sample.web.front.dto.FrontPostDto;
import com.isystk.sample.web.front.service.PostService;
import java.util.Arrays;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

@RestController
@SessionAttributes(types = {MemberPostRegistRestForm.class})
public class MemberPostDetailRestController extends AbstractRestController {

  private static final Logger log = org.slf4j.LoggerFactory
      .getLogger(MemberPostDetailRestController.class);
  @Autowired
  PostService postService;

  @Autowired
  UserHelper userHelper;

  @Autowired
  MemberPostEditRestValidator memberPostEditRestValidator;

  @ModelAttribute("memberPostRegistRestForm")
  public MemberPostRegistRestForm memberPostRegistRestForm() {
    return new MemberPostRegistRestForm();
  }

  @ModelAttribute("memberPostEditRestForm")
  public MemberPostEditRestForm memberPostEditRestForm() {
    return new MemberPostEditRestForm();
  }

  @InitBinder("memberPostEditRestForm")
  public void validatorBinder(WebDataBinder binder) {
    binder.addValidators(memberPostEditRestValidator);
  }

  @Override
  public String getFunctionName() {
    return "API_MEMBER_POSTS_DETAIL";
  }

  /**
   * 指定した投稿IDに紐づく自分の投稿データを取得します。
   *
   * @param postId
   * @param model
   * @return
   */
  @GetMapping(API_V1_MEMBER_POSTS_DETAIL)
  public Resource showDetail(@PathVariable Integer postId, Model model) {

    // 1件取得する
    Optional<FrontPostDto> post = postService.findMyDataById(postId);

    Resource resource = resourceFactory.create();
    resource.setData(Arrays.asList(post.orElse(new FrontPostDto())));
    resource.setMessage(getMessage(MESSAGE_SUCCESS));

    return resource;
  }

  /**
   * 更新処理
   *
   * @param form
   * @param br
   * @param errors
   * @return
   */
  @PutMapping(API_V1_MEMBER_POSTS_EDIT)
  public Resource update(
      @Validated @ModelAttribute("memberPostEditRestForm") MemberPostEditRestForm form,
      BindingResult br, Errors errors) {

    Resource resource = resourceFactory.create();

    // 入力チェックエラーがある場合は、元の画面にもどる
    if (br.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // 入力値を詰め替える
    TPostRepositoryDto tPostDto = ObjectMapperUtils.map(form, TPostRepositoryDto.class);
    // 投稿画像
    tPostDto.setTPostImageList(
        Optional.ofNullable(form.getImageList())
            .map(list -> list.stream()
                .map(image -> {
                  TPostImage tPostImage = new TPostImage();
                  tPostImage.setImageId(image.getImageId());
                  return tPostImage;
                })
                .collect(Collectors.toList())
            )
            .orElse(null)
    );
    // 投稿タグ
    tPostDto.setTPostTagList(
        Optional.ofNullable(form.getTagList())
            .map(list -> list.stream()
                .map(tag -> {
                  TPostTag tPostTag = new TPostTag();
                  tPostTag.setPostTagId(tag.getTagId());
                  return tPostTag;
                })
                .collect(Collectors.toList())
            )
            .orElse(null)
    );
    // 更新する
    postService.update(tPostDto);

    // 1件取得する
    Optional<FrontPostDto> post = postService.findMyDataById(form.getPostId());
    resource.setData(Arrays.asList(post.orElse(new FrontPostDto())));
    resource.setMessage(getMessage(MESSAGE_SUCCESS));

    return resource;
  }

  /**
   * 登録処理
   *
   * @param form
   * @param br
   * @param errors
   * @return
   */
  @PostMapping(API_V1_MEMBER_POSTS_NEW)
  public Resource regist(
      @Validated @ModelAttribute("memberPostRegistRestForm") MemberPostRegistRestForm form,
      BindingResult br, Errors errors) {

    Resource resource = resourceFactory.create();

    // 入力チェックエラーがある場合は、元の画面にもどる
    if (br.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // 入力値からDTOを作成する
    TPostRepositoryDto tPostDto = ObjectMapperUtils.map(form, TPostRepositoryDto.class);
    // ログインユーザーID
    tPostDto.setUserId(userHelper.getLoginUserId());
    // 投稿画像
    tPostDto.setTPostImageList(
        Optional.ofNullable(form.getImageList())
            .map(list -> list.stream()
                .map(image -> {
                  TPostImage tPostImage = new TPostImage();
                  tPostImage.setImageId(image.getImageId());
                  return tPostImage;
                })
                .collect(Collectors.toList())
            )
            .orElse(null)
    );
    // 投稿タグ
    tPostDto.setTPostTagList(
        Optional.
            ofNullable(form.getTagList())
            .map(list -> list.stream()
                .map(tag -> {
                  TPostTag tPostTag = new TPostTag();
                  tPostTag.setPostTagId(tag.getTagId());
                  return tPostTag;
                })
                .collect(Collectors.toList())
            )
            .orElse(null)
    );
    int postId = postService.create(tPostDto);

    // 1件取得する
    Optional<FrontPostDto> post = postService.findMyDataById(postId);
    resource.setData(Arrays.asList(post.orElse(new FrontPostDto())));
    resource.setMessage(getMessage(MESSAGE_SUCCESS));

    return resource;
  }

  /**
   * 削除処理
   *
   * @param postId
   * @return
   */
  @DeleteMapping(API_V1_MEMBER_POSTS_DELETE)
  public Resource delete(@PathVariable Integer postId) {
    postService.delete(postId);

    Resource resource = resourceFactory.create();
    resource.setMessage(getMessage(MESSAGE_SUCCESS));
    return resource;
  }

}

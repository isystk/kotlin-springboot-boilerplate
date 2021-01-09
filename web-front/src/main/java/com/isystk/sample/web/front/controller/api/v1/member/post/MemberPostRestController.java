package com.isystk.sample.web.front.controller.api.v1.member.post;

import static com.isystk.sample.common.Const.MESSAGE_SUCCESS;
import static com.isystk.sample.common.FrontUrl.API_V1_MEMBER_POSTS;

import com.isystk.sample.common.dto.Page;
import com.isystk.sample.common.helper.UserHelper;
import com.isystk.sample.common.util.ObjectMapperUtils;
import com.isystk.sample.domain.dto.TPostCriteria;
import com.isystk.sample.web.base.controller.api.AbstractRestController;
import com.isystk.sample.web.base.controller.api.resource.PageableResource;
import com.isystk.sample.web.base.controller.api.resource.PageableResourceImpl;
import com.isystk.sample.web.front.dto.FrontPostDto;
import com.isystk.sample.web.front.service.PostService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_V1_MEMBER_POSTS)
public class MemberPostRestController extends AbstractRestController {

  private static final Logger log = org.slf4j.LoggerFactory
      .getLogger(MemberPostRestController.class);
  @Autowired
  PostService postService;

  @Autowired
  UserHelper userHelper;

  @Override
  public String getFunctionName() {
    return "API_MEMBER_POSTS";
  }

  /**
   * 会員表示
   *
   * @param query
   * @param model
   * @return
   */
  @GetMapping()
  public PageableResource index(MemberPostRestForm query, Model model) {
    // 入力値を詰め替える
    TPostCriteria criteria = new TPostCriteria();
    criteria.setUserIdEq(userHelper.getLoginUserId());
    criteria.setDeleteFlgFalse(true);
    criteria.setOrderBy("order by update_time desc");

    // 10件区切りで取得する
    Page<FrontPostDto> posts = postService.findAll(criteria, query);

    PageableResource resource = ObjectMapperUtils.map(posts, PageableResourceImpl.class);
    resource.setMessage(getMessage(MESSAGE_SUCCESS));

    return resource;
  }

}

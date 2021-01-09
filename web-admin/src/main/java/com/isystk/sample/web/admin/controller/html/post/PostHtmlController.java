package com.isystk.sample.web.admin.controller.html.post;

import static com.isystk.sample.common.AdminUrl.POST;

import com.isystk.sample.common.dto.Page;
import com.isystk.sample.common.dto.Pageable;
import com.isystk.sample.common.helper.UserHelper;
import com.isystk.sample.common.util.ObjectMapperUtils;
import com.isystk.sample.domain.dto.TPostCriteria;
import com.isystk.sample.domain.entity.TUser;
import com.isystk.sample.domain.repository.MPostTagRepository;
import com.isystk.sample.domain.repository.TPostRepository;
import com.isystk.sample.domain.repository.dto.TPostRepositoryDto;
import com.isystk.sample.web.admin.service.PostService;
import com.isystk.sample.web.base.controller.html.AbstractHtmlController;
import com.isystk.sample.web.base.view.CsvView;
import com.isystk.sample.web.base.view.ExcelView;
import com.isystk.sample.web.base.view.PdfView;
import java.time.LocalTime;
import java.util.List;
import javax.validation.Valid;
import lombok.val;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(POST)
@SessionAttributes(types = {PostSearchForm.class})
public class PostHtmlController extends AbstractHtmlController {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(PostHtmlController.class);
  @Autowired
  PostService postService;

  @Autowired
  TPostRepository postRepository;

  @Autowired
  PostFormValidator postSearchFormValidator;

  @Autowired
  UserHelper userHelper;

  @Autowired
  MPostTagRepository mPostTagRepository;

  @Override
  public String getFunctionName() {
    return "A_POST";
  }

  @ModelAttribute("postSearchForm")
  public PostSearchForm postSearchForm() {
    return new PostSearchForm();
  }

  @InitBinder("postSearchForm")
  public void validatorBinder(WebDataBinder binder) {
    binder.addValidators(postSearchFormValidator);
  }

  /**
   * 一覧画面表示
   *
   * @param form
   * @param model
   * @return
   */
  @GetMapping
  public String index(@ModelAttribute @Valid PostSearchForm form, BindingResult br,
      SessionStatus sessionStatus, RedirectAttributes attributes, Model model) {

    if (br.hasErrors()) {
      setFlashAttributeErrors(attributes, br);
      return "modules/post/list";
    }

    // 10件区切りで取得する
    Page<TPostRepositoryDto> pages = postRepository.findAll(formToCriteria(form), form);

    // 画面に検索結果を渡す
    model.addAttribute("pages", pages);

    return "modules/post/list";
  }

  /**
   * 検索条件を詰める
   *
   * @return
   */
  private TPostCriteria formToCriteria(PostSearchForm form) {

    // 入力値を詰め替える
    TPostCriteria criteria = new TPostCriteria();
    criteria.setPostIdEq(form.getPostId());
    criteria.setUserIdEq(form.getUserId());
    criteria.setTitleLike(form.getTitle());
    if (form.getRegistDateFrom() != null) {
      criteria.setRegistTimeGe(form.getRegistDateFrom().atStartOfDay());
    }
    if (form.getRegistDateTo() != null) {
      criteria.setRegistTimeLe(form.getRegistDateTo().atTime(LocalTime.MAX));
    }
    criteria.setDeleteFlgFalse(true);
    criteria.setOrderBy("order by update_time desc");

    return criteria;
  }

  /**
   * 詳細画面表示
   *
   * @param postId
   * @param model
   * @return
   */
  @GetMapping("{postId}")
  public String show(@PathVariable Integer postId, Model model) {
    TPostRepositoryDto post = postRepository.findById(postId);
    model.addAttribute("post", post);

    TUser tUser = userHelper.getUser(post.getUserId());
    model.addAttribute("userName", String.join(tUser.getFamilyName(), " ", tUser.getName()));

    // タグの一覧
    model.addAttribute("postTagList", mPostTagRepository.findAllSelectList());

    return "modules/post/detail";
  }

  /**
   * 削除処理
   *
   * @param id
   * @return
   */
  @DeleteMapping("{id}")
  public String delete(@PathVariable Integer id) {
    postService.delete(id);
    return "redirect:/post";
  }

  /**
   * CSVダウンロード
   *
   * @param filename
   * @return
   */
  @GetMapping("/download/{filename:.+\\.csv}")
  public CsvView downloadCsv(@PathVariable String filename, PostSearchForm form, Model model) {

    // 全件取得する
    form.setPerpage(Pageable.NO_LIMIT.getPerpage());
    Page<TPostRepositoryDto> pages = postRepository.findAll(formToCriteria(form), form);

    // 詰め替える
    List<PostCsv> csvList = ObjectMapperUtils.mapAll(pages.getData(), PostCsv.class);

    // CSVスキーマクラス、データ、ダウンロード時のファイル名を指定する
    return new CsvView(PostCsv.class, csvList, filename);
  }

  /**
   * Excelダウンロード
   *
   * @param filename
   * @return
   */
  @GetMapping(path = "/download/{filename:.+\\.xlsx}")
  public ModelAndView downloadExcel(@PathVariable String filename, PostSearchForm form,
      Model model) {

    // 全件取得する
    form.setPerpage(Pageable.NO_LIMIT.getPerpage());
    Page<TPostRepositoryDto> pages = postRepository.findAll(formToCriteria(form), form);

    // Excelプック生成コールバック、データ、ダウンロード時のファイル名を指定する
    ExcelView view = new ExcelView(new PostExcel(), pages.getData(), filename);

    return new ModelAndView(view);
  }

  /**
   * PDFダウンロード
   *
   * @param filename
   * @return
   */
  @GetMapping(path = "/download/{filename:.+\\.pdf}")
  public ModelAndView downloadPdf(@PathVariable String filename, PostSearchForm form, Model model) {

    // 全件取得する
    form.setPerpage(Pageable.NO_LIMIT.getPerpage());
    Page<TPostRepositoryDto> pages = postRepository.findAll(formToCriteria(form), form);

    // 帳票レイアウト、データ、ダウンロード時のファイル名を指定する
    PdfView view = new PdfView("reports/post.jrxml", pages.getData(), filename);

    return new ModelAndView(view);
  }
}
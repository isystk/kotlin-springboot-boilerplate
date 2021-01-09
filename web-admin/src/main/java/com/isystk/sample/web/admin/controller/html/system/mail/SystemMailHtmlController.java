package com.isystk.sample.web.admin.controller.html.system.mail;

import static com.isystk.sample.common.AdminUrl.SYSTEM_MAIL;

import com.isystk.sample.common.dto.Page;
import com.isystk.sample.domain.dto.MMailTemplateCriteria;
import com.isystk.sample.domain.repository.MMailTemplateRepository;
import com.isystk.sample.domain.repository.dto.MMailTemplateRepositoryDto;
import com.isystk.sample.web.base.controller.html.AbstractHtmlController;
import lombok.val;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(SYSTEM_MAIL)
@SessionAttributes(types = {SystemMailSearchForm.class})
public class SystemMailHtmlController extends AbstractHtmlController {

  private static final Logger log = org.slf4j.LoggerFactory
      .getLogger(SystemMailHtmlController.class);
  @Autowired
  MMailTemplateRepository mMailTemplateRepository;

  @Override
  public String getFunctionName() {
    return "A_SYSTEM_MAIL";
  }

  /**
   * 一覧画面表示
   *
   * @param form
   * @param model
   * @return
   */
  @GetMapping
  public String index(SystemMailSearchForm form, Model model) {

    // 10件区切りで取得する
    Page<MMailTemplateRepositoryDto> pages = mMailTemplateRepository.findAll(formToCriteria(form), form);

    // 画面に検索結果を渡す
    model.addAttribute("pages", pages);

    return "modules/system/mail/list";
  }

  /**
   * 検索条件を詰める
   *
   * @return
   */
  private MMailTemplateCriteria formToCriteria(SystemMailSearchForm form) {

    // 入力値を詰め替える
    MMailTemplateCriteria criteria = new MMailTemplateCriteria();
    criteria.setTemplateDivEq(form.getTemplateDiv());
    criteria.setDeleteFlgFalse(true);
    criteria.setOrderBy("order by mail_template_id asc");

    return criteria;
  }

  /**
   * 詳細画面表示
   *
   * @param mailTemplateId
   * @param model
   * @return
   */
  @GetMapping("{mailTemplateId}")
  public String show(@PathVariable Integer mailTemplateId, Model model) {
    MMailTemplateRepositoryDto mMailTemplateRepositoryDto = mMailTemplateRepository.findById(mailTemplateId);
    model.addAttribute("mMailTemplateRepositoryDto", mMailTemplateRepositoryDto);

    return "modules/system/mail/detail";
  }

}
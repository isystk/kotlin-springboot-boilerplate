package com.isystk.sample.web.base.controller.html;

import static com.isystk.sample.common.Const.MAV_ERRORS;

import com.isystk.sample.common.FunctionNameAware;
import com.isystk.sample.web.base.controller.BaseController;
import org.slf4j.Logger;
import org.springframework.ui.Model;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 基底HTMLコントローラー
 */
public abstract class AbstractHtmlController extends BaseController implements FunctionNameAware {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(AbstractHtmlController.class);

  /**
   * 入力チェックエラーがある場合はtrueを返します。
   *
   * @param model
   * @return
   */
  public boolean hasErrors(Model model) {
    Object errors = model.asMap().get(MAV_ERRORS);

    if (errors != null && errors instanceof BeanPropertyBindingResult) {
      BeanPropertyBindingResult br = ((BeanPropertyBindingResult) errors);

      if (br.hasErrors()) {
        return true;
      }
    }

    return false;
  }

  /**
   * リダイレクト先に入力エラーを渡します。
   *
   * @param attributes
   * @param result
   */
  public void setFlashAttributeErrors(RedirectAttributes attributes, BindingResult result) {
    attributes.addFlashAttribute(MAV_ERRORS, result);
  }
}

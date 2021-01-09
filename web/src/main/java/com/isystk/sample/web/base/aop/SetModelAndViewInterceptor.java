package com.isystk.sample.web.base.aop;

import static com.isystk.sample.common.Const.GLOBAL_DANGER_MESSAGE;
import static com.isystk.sample.common.Const.MAV_CONST;
import static com.isystk.sample.common.Const.MAV_ERRORS;
import static com.isystk.sample.common.Const.MAV_LOGIN_USER;
import static com.isystk.sample.common.Const.MAV_PULLDOWN_OPTION;
import static com.isystk.sample.common.Const.VALIDATION_ERROR;

import com.isystk.sample.common.util.MessageUtils;
import com.isystk.sample.web.base.filter.UserIdAware;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public class SetModelAndViewInterceptor extends BaseHandlerInterceptor {

  private static final Logger log = org.slf4j.LoggerFactory
      .getLogger(SetModelAndViewInterceptor.class);
  @Value("${application.imageUploadLocation:#{systemProperties['java.io.tmpdir']}}") // 設定ファイルに定義されたアップロード先を取得する
  String imageUploadLocation;

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    // コントローラーの動作後
    if (isRestController(handler)) {
      // APIの場合はスキップする
      return;
    }

    if (modelAndView == null) {
      return;
    }

    Locale locale = LocaleContextHolder.getLocale();
    String pulldownOption = MessageUtils.getMessage(MAV_PULLDOWN_OPTION, locale);

    // ログインユーザーID
    UserIdAware user = null;
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    if (authentication.getPrincipal() instanceof UserIdAware) {
      user = UserIdAware.class.cast(authentication.getPrincipal());
    }

    // 定数定義を画面に渡す
    Map<String, Object> constants = new HashMap<>();
    constants.put(MAV_PULLDOWN_OPTION, pulldownOption);
    modelAndView.addObject(MAV_CONST, constants);
    modelAndView.addObject(MAV_LOGIN_USER, user);

    // 入力エラーを画面オブジェクトに設定する
    retainValidateErrors(modelAndView);
  }

  /**
   * 入力エラーを画面オブジェクトに設定する
   *
   * @param modelAndView
   */
  protected void retainValidateErrors(ModelAndView modelAndView) {
    ModelMap model = modelAndView.getModelMap();

    if (model != null && model.containsAttribute(MAV_ERRORS)) {
      Object errors = model.get(MAV_ERRORS);

      if (errors != null && errors instanceof BeanPropertyBindingResult) {
        BeanPropertyBindingResult br = ((BeanPropertyBindingResult) errors);

        if (br.hasErrors()) {
          String formName = br.getObjectName();
          String key = BindingResult.MODEL_KEY_PREFIX + formName;
          model.addAttribute(key, errors);
          model.addAttribute(GLOBAL_DANGER_MESSAGE, MessageUtils.getMessage(VALIDATION_ERROR));
        }
      }
    }
  }
}

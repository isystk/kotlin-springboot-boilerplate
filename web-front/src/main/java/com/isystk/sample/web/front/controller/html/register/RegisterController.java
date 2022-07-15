package com.isystk.sample.web.front.controller.html.register;

import static com.isystk.sample.common.FrontUrl.REGISTER;

import com.isystk.sample.common.exception.NoDataFoundException;
import com.isystk.sample.common.helper.UserHelper;
import com.isystk.sample.common.util.ObjectMapperUtils;
import com.isystk.sample.domain.entity.User;
import com.isystk.sample.web.base.controller.html.AbstractHtmlController;
import com.isystk.sample.web.front.service.RegisterService;
import java.util.Optional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * 会員登録
 */
@Controller
@RequestMapping(path = REGISTER)
public class RegisterController extends AbstractHtmlController {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(RegisterController.class);
  @Autowired
  RegisterService registerService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  RegisterFormValidator registerFormValidator;

  @Autowired
  UserHelper userHelper;

  @ModelAttribute("form")
  public RegisterForm initForm() {
    return new RegisterForm();
  }

  @InitBinder("form")
  public void validatorBinder(WebDataBinder binder) {
    binder.addValidators(registerFormValidator);
  }

  @Override
  public String getFunctionName() {
    return "F_REGISTER";
  }

  /**
   * 仮会員登録処理
   *
   * @param registerForm
   * @param br
   * @return
   */
  @PostMapping
  public String index(@Validated @ModelAttribute("form") RegisterForm registerForm, BindingResult br, RedirectAttributes attributes) {

    // 入力チェックエラーがある場合は、元の画面にもどる
    if (br.hasErrors()) {
        setFlashAttributeErrors(attributes, br);
      return "modules/index";
    }

    // 入力値からDTOを作成する
    User inputUser = ObjectMapperUtils.map(registerForm, User.class);
    String password = registerForm.getPassword();

    // パスワードをハッシュ化する
    inputUser.setPassword(passwordEncoder.encode(password));

    // 仮会員登録
    registerService.registTemporary(inputUser);

    return "redirect:/register/sendMail";
  }

  /**
   * 仮会員登録メール再送信
   *
   * @return
   */
  @PostMapping("/resend")
  public String resend() {

    var userId = Optional.of(userHelper.getLoginUserId()).orElseThrow(() -> new NoDataFoundException("未ログイン状態です"));

    // 仮会員登録メール再送信
    registerService.sendMail(userId);

    return "redirect:/register/sendMail";
  }

  /**
   * 本会員登録処理
   *
   * @param onetimeKey
   * @return
   */
  @GetMapping("/valified/{onetimeKey}")
  public String valified(@PathVariable String onetimeKey, Model model) {

    // 本会員登録
    registerService.registComplete(onetimeKey);

    return "redirect:/register/complete";
  }

}

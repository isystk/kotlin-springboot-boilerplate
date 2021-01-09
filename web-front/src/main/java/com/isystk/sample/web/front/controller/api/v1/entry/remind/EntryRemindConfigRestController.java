package com.isystk.sample.web.front.controller.api.v1.entry.remind;

import static com.isystk.sample.common.Const.MESSAGE_SUCCESS;
import static com.isystk.sample.common.FrontUrl.API_V1_ENTRY_REMIND_CONFIG;

import com.isystk.sample.common.exception.NoDataFoundException;
import com.isystk.sample.common.exception.ValidationErrorException;
import com.isystk.sample.web.base.controller.api.AbstractRestController;
import com.isystk.sample.web.base.controller.api.resource.Resource;
import com.isystk.sample.web.front.service.EntryRemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 会員パスワード変更
 */
@RestController
@RequestMapping(path = API_V1_ENTRY_REMIND_CONFIG, produces = MediaType.APPLICATION_JSON_VALUE)
public class EntryRemindConfigRestController extends AbstractRestController {

  @Autowired
  EntryRemindService entryRemindService;

  @Autowired
  PasswordEncoder passwordEncoder;

  @Autowired
  EntryRemindConfigRestFormValidator entryRemindConfigRestFormValidator;

  @ModelAttribute("entryRemindConfigRestForm")
  public EntryRemindConfigRestForm entryRemindConfigRestForm() {
    return new EntryRemindConfigRestForm();
  }

  @InitBinder("entryRemindConfigRestForm")
  public void validatorBinder(WebDataBinder binder) {
    binder.addValidators(entryRemindConfigRestFormValidator);
  }

  @Override
  public String getFunctionName() {
    return "API_ENTRY_REMIND_CONFIG";
  }

  /**
   * パスワード変更画面表示
   *
   * @param onetimeKey
   * @return
   */
  @GetMapping("{onetimeKey}")
  public Resource config(@PathVariable String onetimeKey,
      @ModelAttribute EntryRemindConfigRestForm form, Model model) {

    Resource resource = resourceFactory.create();

    // ワンタイムキーからユーザーIDを取得する
    var tUserOnetimePass = entryRemindService.getTUserOnetimePass(onetimeKey);
    if (tUserOnetimePass == null) {
      throw new NoDataFoundException("指定されたワンタイムキーが見つかりません。[onetimeKey=" + onetimeKey + "]");
    }

    form.setOnetimeKey(onetimeKey);

    resource.setMessage(getMessage(MESSAGE_SUCCESS));

    return resource;
  }

  /**
   * パスワード変更処理
   *
   * @return
   */
  @PostMapping
  public Resource changePassword(@Validated @ModelAttribute EntryRemindConfigRestForm form,
      BindingResult br, Errors errors) {

    Resource resource = resourceFactory.create();

    // 入力チェックエラーがある場合は、元の画面にもどる
    if (br.hasErrors()) {
      throw new ValidationErrorException(errors);
    }

    // ワンタイムキーからユーザーIDを取得する
    var tUserOnetimePass = entryRemindService.getTUserOnetimePass(form.getOnetimeKey());
    if (tUserOnetimePass == null) {
      throw new NoDataFoundException(
          "指定されたワンタイムキーが見つかりません。[onetimeKey=" + form.getOnetimeKey() + "]");
    }

    // パスワードをハッシュ化する
    String password = passwordEncoder.encode(form.getPassword());

    // パスワード変更ワンタイムパス登録
    entryRemindService.changePassword(form.getOnetimeKey(), password);

    resource.setMessage(getMessage(MESSAGE_SUCCESS));

    return resource;
  }

}

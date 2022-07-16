package com.isystk.sample.web.base.controller.html;

import com.isystk.sample.common.dto.ID;
import com.isystk.sample.web.base.controller.IDTypeEditor;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * HTMLコントローラーアドバイス
 */
@ControllerAdvice(assignableTypes = {AbstractHtmlController.class}) // RestControllerでは動作させない
public class HtmlControllerAdvice {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(HtmlControllerAdvice.class);

  @InitBinder
  public void initBinder(WebDataBinder binder, HttpServletRequest request) {
    // 文字列フィールドが未入力の場合に、空文字ではなくNULLをバインドする
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));

    // 文字列をIDに変換する
    binder.registerCustomEditor(ID.class, new IDTypeEditor());
//
//        // idカラムを入力値で上書きしない
//        binder.setDisallowedFields("*.id");

    // versionカラムを入力値で上書きしない
    binder.setDisallowedFields("*.version");
  }
}

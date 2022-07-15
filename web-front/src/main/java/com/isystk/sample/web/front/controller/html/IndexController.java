package com.isystk.sample.web.front.controller.html;


import static com.isystk.sample.common.FrontUrl.TOP;

import com.isystk.sample.web.base.controller.html.AbstractHtmlController;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(TOP)
public class IndexController extends AbstractHtmlController {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(IndexController.class);

  @Override
  public String getFunctionName() {
    return "F_TOP";
  }

  @GetMapping({"/", "{path:(?!^static|oauth|swagger-ui$).*}/**"})
  public String index(Model model) {
    return "modules/index";
  }

}

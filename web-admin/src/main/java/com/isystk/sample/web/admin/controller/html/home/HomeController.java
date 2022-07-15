package com.isystk.sample.web.admin.controller.html.home;

import static com.isystk.sample.common.AdminUrl.HOME;

import com.isystk.sample.web.base.controller.html.AbstractHtmlController;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(HOME)
public class HomeController extends AbstractHtmlController {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(HomeController.class);

  @Override
  public String getFunctionName() {
    return "A_HOME";
  }

  @GetMapping
  public String index(Model model) {
    return "modules/home/index";
  }

}

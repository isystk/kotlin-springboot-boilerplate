package com.isystk.sample.web.admin.controller.html.stock;

import static com.isystk.sample.common.AdminUrl.STOCKS;

import com.isystk.sample.domain.dto.StockRepositoryDto;
import com.isystk.sample.web.admin.service.StockService;
import com.isystk.sample.web.base.controller.html.AbstractHtmlController;
import java.math.BigInteger;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(STOCKS)
public class StockDetailController extends AbstractHtmlController {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(StockDetailController.class);
  @Autowired
  StockService stockService;

  @Override
  public String getFunctionName() {
    return "A_STOCK_DETAIL";
  }

  /**
   * 詳細画面表示
   *
   * @param stockId
   * @param model
   * @return
   */
  @GetMapping("{stockId}")
  public String show(@PathVariable BigInteger stockId, Model model) {
    StockRepositoryDto stock = stockService.findById(stockId);
    model.addAttribute("stock", stock);
    return "modules/stock/detail";
  }

}

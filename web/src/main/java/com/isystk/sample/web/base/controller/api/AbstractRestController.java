package com.isystk.sample.web.base.controller.api;

import com.isystk.sample.common.FunctionNameAware;
import com.isystk.sample.web.base.controller.BaseController;
import com.isystk.sample.web.base.controller.api.resource.ResourceFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 基底APIコントローラー
 */
@ResponseStatus(HttpStatus.OK)
public abstract class AbstractRestController extends BaseController implements FunctionNameAware {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(AbstractRestController.class);
  @Autowired
  protected ResourceFactory resourceFactory;
}

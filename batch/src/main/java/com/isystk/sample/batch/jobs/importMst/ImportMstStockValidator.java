package com.isystk.sample.batch.jobs.importMst;

import com.isystk.sample.common.validator.AbstractValidator;
import java.util.Objects;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ImportMstStockValidator extends AbstractValidator<ImportMstStockDto> {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(
      ImportMstStockValidator.class);

  @Override
  protected void doValidate(ImportMstStockDto dto, Errors errors) {

    if (dto.getPosition() == 1 && !Objects.equals(dto.getName(), "商品名")) {
      errors.rejectValue("name", "importMstJob.invalidMstStock");
    }
  }
}

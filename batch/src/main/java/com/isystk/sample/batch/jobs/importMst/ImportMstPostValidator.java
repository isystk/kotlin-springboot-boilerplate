package com.isystk.sample.batch.jobs.importMst;

import com.isystk.sample.common.validator.AbstractValidator;
import java.util.Objects;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

@Component
public class ImportMstPostValidator extends AbstractValidator<ImportMstPostDto> {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(ImportMstPostValidator.class);

  @Override
  protected void doValidate(ImportMstPostDto dto, Errors errors) {

    if (dto.getPosition() == 1 && !Objects.equals(dto.getPostTagId(), "Wyn")) {
      errors.rejectValue("postTagId", "importMstJob.invalidMstPostId");
    }
  }
}

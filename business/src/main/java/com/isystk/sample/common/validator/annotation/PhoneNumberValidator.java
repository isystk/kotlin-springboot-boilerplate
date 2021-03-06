package com.isystk.sample.common.validator.annotation;

import static com.isystk.sample.common.util.ValidateUtils.isEmpty;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 入力チェック（電話番号）
 */
public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

  protected final static Logger log = LoggerFactory.getLogger(PhoneNumberValidator.class);
  private Pattern pattern;

  @Override
  public void initialize(PhoneNumber phoneNumber) {
    try {
      pattern = Pattern.compile(phoneNumber.regexp());
    } catch (PatternSyntaxException e) {
      log.error("invalid regular expression.", e);
      throw e;
    }
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    boolean isValid = false;

    if (isEmpty(value)) {
      isValid = true;
    } else {
      Matcher m = pattern.matcher(value);

      if (m.matches()) {
        isValid = true;
      }
    }

    return isValid;
  }
}

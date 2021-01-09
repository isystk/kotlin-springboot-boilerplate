package com.isystk.sample.batch.jobs;

import com.isystk.sample.batch.context.BatchContext;
import com.isystk.sample.batch.context.BatchContextHolder;
import org.slf4j.Logger;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.Validator;

/**
 * 基底プロセッサー
 *
 * @param <I>
 * @param <O>
 */
public abstract class BaseItemProcessor<I, O> implements ItemProcessor<I, O> {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(BaseItemProcessor.class);

  @Override
  public O process(I item) {
    Validator validator = getValidator();
    BatchContext context = BatchContextHolder.getContext();

    // 対象件数を加算する
    context.increaseTotalCount();

    if (validator != null) {
      DataBinder binder = new DataBinder(item);
      binder.setValidator(validator);
      binder.validate();

      BindingResult result = binder.getBindingResult();
      if (result.hasErrors()) {
        // バリデーションエラーがある場合
        onValidationError(context, result, item);

        // エラー件数をカウントする
        increaseErrorCount(context, result, item);

        // nullを返すとItemWriterに渡されない
        return null;
      }
    }

    // 実処理
    O output = doProcess(context, item);

    // 処理件数をカウントする
    increaseProcessCount(context, item);

    return output;
  }

  /**
   * エラー件数を加算します。
   *
   * @param context
   * @param result
   * @param item
   */
  protected void increaseErrorCount(BatchContext context, BindingResult result, I item) {
    context.increaseErrorCount();
  }

  /**
   * 処理件数を加算します。
   *
   * @param context
   * @param item
   */
  protected void increaseProcessCount(BatchContext context, I item) {
    context.increaseProcessCount();
  }

  /**
   * 対象件数を加算します。
   *
   * @param context
   * @param item
   */
  protected void increaseTotalCount(BatchContext context, I item) {
    context.increaseProcessCount();
  }

  /**
   * バリデーションエラーが発生した場合に処理します。
   *
   * @param context
   * @param result
   * @param item
   */
  protected abstract void onValidationError(BatchContext context, BindingResult result, I item);

  /**
   * 実処理を実施します。
   *
   * @param context
   * @param item
   * @return
   */
  protected abstract O doProcess(BatchContext context, I item);

  /**
   * バリデーターを取得します。
   *
   * @return
   */
  protected abstract Validator getValidator();

  /**
   * 例外発生時のデフォルト実装
   *
   * @param item
   * @param e
   */
  @OnProcessError
  protected void onProcessError(I item, Exception e) {
    log.error("failed to process item.", e);
    throw new IllegalStateException(e);
  }
}

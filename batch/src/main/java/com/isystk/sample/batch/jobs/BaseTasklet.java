package com.isystk.sample.batch.jobs;

import com.isystk.sample.batch.context.BatchContext;
import com.isystk.sample.batch.context.BatchContextHolder;
import com.isystk.sample.batch.item.ItemPosition;
import java.io.IOException;
import org.slf4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.annotation.OnProcessError;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

/**
 * 基底タスクレット
 */
public abstract class BaseTasklet<I extends ItemPosition> implements Tasklet {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(BaseTasklet.class);

  /**
   * メインメソッド
   *
   * @param contribution
   * @param chunkContext
   * @return
   * @throws Exception
   */
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws IOException {
    BatchContext context = BatchContextHolder.getContext();

    // 実処理
    doProcess(context);

    return RepeatStatus.FINISHED;
  }

  /**
   * 実処理を実施します。
   *
   * @param context
   * @param item
   * @return
   */
  protected abstract void doProcess(BatchContext context);

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

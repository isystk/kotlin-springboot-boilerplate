package com.isystk.sample.batch.listener;

import static com.isystk.sample.batch.BatchConst.MDC_BATCH_ID;

import com.isystk.sample.batch.context.BatchContext;
import com.isystk.sample.batch.context.BatchContextHolder;
import com.isystk.sample.common.util.DateUtils;
import com.isystk.sample.common.util.MDCUtils;
import com.isystk.sample.domain.dao.AuditInfoHolder;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.MDC;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobInstance;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

public abstract class BaseJobExecutionListener extends JobExecutionListenerSupport {

  // yyyy/MM/dd HH:mm:ss
  private static final DateTimeFormatter YYYY_MM_DD_HHmmss = DateTimeFormatter
      .ofPattern("yyyy/MM/dd HH:mm:ss");
  private static final Logger log = org.slf4j.LoggerFactory.getLogger(
      BaseJobExecutionListener.class);

  @Override
  public void beforeJob(JobExecution jobExecution) {
    String batchId = getBatchId();
    String batchName = getBatchName();
    Date startTime = jobExecution.getStartTime();
    LocalDateTime startDateTime = DateUtils.toLocalDateTime(startTime);

    MDCUtils.putIfAbsent(MDC_BATCH_ID, batchId);

    log.info("*********************************************");
    log.info("* バッチID : {}", batchId);
    log.info("* バッチ名 : {}", batchName);
    log.info("* 開始時刻 : {}", DateUtils.format(startTime, YYYY_MM_DD_HHmmss));
    log.info("*********************************************");

    // 監査情報を設定する
    AuditInfoHolder.set(batchId, startDateTime);

    // コンテキストを設定する
    BatchContext context = BatchContextHolder.getContext();
    context.set(batchId, batchName, startDateTime);

    // 機能別の初期化処理を呼び出す
    before(jobExecution, context);
  }

  @Override
  public void afterJob(JobExecution jobExecution) {
    // コンテキストを取り出す
    BatchContext context = BatchContextHolder.getContext();

    // 機能別の終了処理を呼び出す
    try {
      after(jobExecution, context);
    } catch (Throwable t) {
      log.error("exception occurred. ", t);
      throw new IllegalStateException(t);
    } finally {
      // 共通の終了処理
      try {
        String batchId = context.getBatchId();
        String batchName = context.getBatchName();
        BatchStatus jobStatus = jobExecution.getStatus();
        Date endTime = jobExecution.getEndTime();

        if (log.isDebugEnabled()) {
          Long jobId = jobExecution.getJobId();
          JobInstance jobInstance = jobExecution.getJobInstance();
          long jobInstanceId = jobInstance.getInstanceId();
          log.debug("job executed. [job={}(JobInstanceId:{} status:{})] in {}ms", jobId,
              jobInstanceId,
              jobStatus, took(jobExecution));
          jobExecution.getStepExecutions()
              .forEach(
                  s -> log.debug("step executed. [step={}] in {}ms", s.getStepName(), took(s)));
        }

        if (!jobStatus.isRunning()) {
          log.info("*********************************************");
          log.info("* バッチID   : {}", batchId);
          log.info("* バッチ名   : {}", batchName);
          log.info("* ステータス : {}", jobStatus.getBatchStatus().toString());
          log.info("* 対象件数   : {}", context.getTotalCount());
          log.info("* 処理件数   : {}", context.getProcessCount());
          log.info("* エラー件数 : {}", context.getErrorCount());
          log.info("* 終了時刻   : {}", DateUtils.format(endTime, YYYY_MM_DD_HHmmss));
          log.info("*********************************************");
        }
      } finally {
        MDC.remove(MDC_BATCH_ID);

        // 監査情報をクリアする
        AuditInfoHolder.clear();

        // ジョブコンテキストをクリアする
        context.clear();
      }
    }
  }

  protected long took(JobExecution jobExecution) {
    return jobExecution.getEndTime().getTime() - jobExecution.getStartTime().getTime();
  }

  protected long took(StepExecution stepExecution) {
    return stepExecution.getEndTime().getTime() - stepExecution.getStartTime().getTime();
  }

  /**
   * バッチIDを返します。
   *
   * @return
   */
  protected abstract String getBatchId();

  /**
   * バッチ名を返します。
   *
   * @return
   */
  protected abstract String getBatchName();

  /**
   * 機能別の初期化処理を呼び出す
   *
   * @param jobExecution
   * @param context
   */
  protected void before(JobExecution jobExecution, BatchContext context) {
  }

  /**
   * 機能別の終了処理を呼び出す
   *
   * @param jobExecution
   * @param context
   */
  protected void after(JobExecution jobExecution, BatchContext context) {
  }
}

package com.isystk.sample.batch.jobs.solrRegist;

import com.isystk.sample.batch.context.BatchContext;
import com.isystk.sample.batch.jobs.BaseTasklet;
import com.isystk.sample.batch.service.SolrPostService;
import java.io.IOException;
import org.slf4j.Logger;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * インデックス生成
 */
public class SolrRegistTasklet extends BaseTasklet<SolrRegistPostDto> {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(SolrRegistTasklet.class);
  @Autowired
  SolrPostService solrPostService;

  @Override
  public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext)
      throws IOException {
    return super.execute(contribution, chunkContext);
  }

  @Override
  protected void doProcess(BatchContext context) {
    // Solrの投稿インデックスを更新します。
    solrPostService.refresh();
  }

}

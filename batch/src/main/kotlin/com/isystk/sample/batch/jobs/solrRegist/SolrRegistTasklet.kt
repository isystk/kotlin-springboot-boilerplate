package com.isystk.sample.batch.jobs.solrRegist

import com.isystk.sample.batch.context.BatchContext
import com.isystk.sample.batch.jobs.BaseTasklet
import com.isystk.sample.batch.service.SolrPostService
import org.slf4j.LoggerFactory
import org.springframework.batch.core.StepContribution
import org.springframework.batch.core.scope.context.ChunkContext
import org.springframework.batch.repeat.RepeatStatus
import org.springframework.beans.factory.annotation.Autowired
import java.io.IOException

/**
 * インデックス生成
 */
class SolrRegistTasklet : BaseTasklet<SolrRegistPostDto?>() {
    @Autowired
    var solrPostService: SolrPostService? = null

    @Throws(IOException::class)
    override fun execute(contribution: StepContribution, chunkContext: ChunkContext): RepeatStatus? {
        return super.execute(contribution, chunkContext)
    }

    override fun doProcess(context: BatchContext?) {
        // Solrの投稿インデックスを更新します。
        solrPostService!!.refresh()
    }

    companion object {
        private val log = LoggerFactory.getLogger(SolrRegistTasklet::class.java)
    }
}
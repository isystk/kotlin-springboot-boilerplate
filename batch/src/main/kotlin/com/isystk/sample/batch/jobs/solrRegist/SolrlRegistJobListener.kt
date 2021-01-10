package com.isystk.sample.batch.jobs.solrRegist

import com.isystk.sample.batch.listener.BaseJobExecutionListener

class SolrlRegistJobListener : BaseJobExecutionListener() {
    protected override val batchId: String
        protected get() = "BATCH_001"

    protected override val batchName: String
        protected get() = "インデックス生成"
}
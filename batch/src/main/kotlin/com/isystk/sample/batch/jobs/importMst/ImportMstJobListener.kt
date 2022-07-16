package com.isystk.sample.batch.jobs.importMst

import com.isystk.sample.batch.listener.BaseJobExecutionListener

class ImportMstJobListener : BaseJobExecutionListener() {
    protected override val batchId: String
        protected get() = "BATCH_002"
    protected override val batchName: String
        protected get() = "マスタ取り込み"
}
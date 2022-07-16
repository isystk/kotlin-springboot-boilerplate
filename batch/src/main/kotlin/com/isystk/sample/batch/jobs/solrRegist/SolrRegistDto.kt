package com.isystk.sample.batch.jobs.solrRegist

import com.isystk.sample.batch.item.ItemPosition

class SolrRegistDto : ItemPosition {

    // 取り込み元ファイル
    override var sourceName: String? = null
    override var position = 0
}
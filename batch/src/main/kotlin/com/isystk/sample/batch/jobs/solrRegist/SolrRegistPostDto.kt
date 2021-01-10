package com.isystk.sample.batch.jobs.solrRegist

import com.isystk.sample.batch.item.ItemPosition
import java.time.LocalDateTime
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class SolrRegistPostDto : ItemPosition {
    var postId: @NotNull Int? = null
    var userId: @NotNull Int? = null
    var title: @NotEmpty String? = null
    var text: @NotEmpty String? = null
    var registTime: @NotEmpty LocalDateTime? = null

    // 取り込み元ファイル
    override var sourceName: String? = null
    override var position = 0

}
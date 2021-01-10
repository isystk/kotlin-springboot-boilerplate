package com.isystk.sample.batch.jobs.importMst

import com.isystk.sample.batch.item.ItemPosition
import javax.validation.constraints.NotEmpty

class ImportMstPostDto : ItemPosition {
    // タグID
    var postTagId: @NotEmpty String? = null

    // タグ名称
    var name: @NotEmpty String? = null

    // 取り込み元ファイル
    override var sourceName: String? = null
    override var position = 0

}
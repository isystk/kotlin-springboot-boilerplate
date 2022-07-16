package com.isystk.sample.batch.jobs.importMst

import com.isystk.sample.batch.item.ItemPosition
import javax.validation.constraints.NotEmpty

class ImportMstStockDto : ItemPosition {
    /** 商品名  */
    var name: @NotEmpty String? = null

    /** 説明文  */
    var detail: @NotEmpty String? = null

    /** 価格  */
    var price: @NotEmpty String? = null

    /** 画像ファイル名  */
    var imgpath: @NotEmpty String? = null

    /** 在庫数  */
    var quantity: @NotEmpty String? = null

    // 取り込み元ファイル
    override var sourceName: String? = null
    override var position = 0
}
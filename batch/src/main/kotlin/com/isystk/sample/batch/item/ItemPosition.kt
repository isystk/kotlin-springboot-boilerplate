package com.isystk.sample.batch.item

/**
 * 走査位置
 */
interface ItemPosition {
    var sourceName: String?
    var position: Int
    val isFirst: Boolean
        get() = position == 1
}
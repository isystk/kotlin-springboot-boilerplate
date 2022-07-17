package com.isystk.sample.common.dto

/**
 * ページファクトリ
 */
interface PageFactory {
    /**
     * インスタンスを生成して返します。
     *
     * @param data
     * @param pageable
     * @param count
     * @param <T>
     * @return
    </T> */
    fun <T> create(data: List<T>, pageable: Pageable, count: Long): Page<T>
}
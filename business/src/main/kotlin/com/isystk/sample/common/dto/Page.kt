package com.isystk.sample.common.dto

/**
 * ページネーションを利用している場合に、ページ数や現在のページ数を保持する
 *
 * @param <T>
</T> */
interface Page<T> : Pageable {
    /**
     * データを返します。
     *
     * @return
     */
    val data: List<T>

    /**
     * データ件数を返します。
     *
     * @return
     */
    val count: Long

    /**
     * ページ数を返します。
     *
     * @return
     */
    val totalPages: Int
}
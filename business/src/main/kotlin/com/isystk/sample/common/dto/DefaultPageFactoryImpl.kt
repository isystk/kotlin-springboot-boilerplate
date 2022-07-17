package com.isystk.sample.common.dto

/**
 * ページファクトリのデフォルト実装
 */
class DefaultPageFactoryImpl : PageFactory {
    /**
     * インスタンスを生成して返します。
     *
     * @param data
     * @param pageable
     * @param count
     * @param <T>
     * @return
    </T> */
    override fun <T> create(data: List<T>, pageable: Pageable, count: Long): Page<T> {
        return PageImpl(data, pageable, count)
    }
}
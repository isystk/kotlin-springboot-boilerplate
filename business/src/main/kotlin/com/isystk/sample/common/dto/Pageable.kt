package com.isystk.sample.common.dto

/**
 * ページング可能であることを示す
 */
interface Pageable {
    /**
     * @return
     */
    fun page(): Int

    /**
     * @return
     */
    fun perpage(): Int

    companion object {
        val DEFAULT: Pageable = DefaultPageable(1, 10)
        val NO_LIMIT: Pageable = DefaultPageable(1, Int.MAX_VALUE)
    }
}
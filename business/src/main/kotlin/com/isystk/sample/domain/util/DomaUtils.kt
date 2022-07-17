package com.isystk.sample.domain.util

import com.isystk.sample.common.dto.Pageable
import org.seasar.doma.jdbc.SelectOptions

/**
 * Doma関連ユーティリティ
 */
object DomaUtils {
    /**
     * SearchOptionsを作成して返します。
     *
     * @return
     */
    fun createSelectOptions(): SelectOptions {
        return SelectOptions.get()
    }

    /**
     * SearchOptionsを作成して返します。
     *
     * @param pageable
     * @return
     */
    fun createSelectOptions(pageable: Pageable): SelectOptions {
        val page = pageable.page()
        val perpage = pageable.perpage()
        return createSelectOptions(page, perpage)
    }

    /**
     * SearchOptionsを作成して返します。
     *
     * @param page
     * @param perpage
     * @return
     */
    fun createSelectOptions(page: Int, perpage: Int): SelectOptions {
        val offset = (page - 1) * perpage
        return SelectOptions.get().offset(offset).limit(perpage)
    }
}
package com.isystk.sample.web.base.controller.api.resource

/**
 * リソースファクトリ
 */
interface ResourceFactory {
    /**
     * インスタンスを作成します。
     *
     * @return
     */
    fun create(): Resource
}
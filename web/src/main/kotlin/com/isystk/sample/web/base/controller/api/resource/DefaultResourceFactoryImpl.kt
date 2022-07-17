package com.isystk.sample.web.base.controller.api.resource

/**
 * リソースファクトリのデフォルト実装
 */
class DefaultResourceFactoryImpl : ResourceFactory {
    /**
     * インスタンスを作成します。
     *
     * @return
     */
    override fun create(): Resource {
        return ResourceImpl()
    }
}
package com.isystk.sample.common.helper

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

/**
 * 環境ヘルパー
 */
@Component("env")
class EnvHelper {
    @Value("\${spring.profiles}")
    var profile: // 設定ファイルに定義されたprofilesを取得する
            String? = null

    /**
     * ローカル環境かどうか
     */
    val isLocal: Boolean
        get() = "local" == profile

    /**
     * ステージング環境かどうか
     */
    val isStaging: Boolean
        get() = "staging" == profile

    /**
     * 本番環境かどうか
     */
    val isProduction: Boolean
        get() = "production" == profile

}
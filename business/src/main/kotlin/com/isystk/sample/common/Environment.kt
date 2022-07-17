package com.isystk.sample.common

import java.io.File

/**
 * 環境情報
 */
enum class Environment {
    LOCAL, DEV, STG, PRODUCTION;

    companion object {
        private const val SPRING_PROFILES_ACTIVE = "spring.profiles.active"
        private const val LOCAL_PROFILE = "local"
        private const val DEV_PROFILE = "development"
        private const val STG_PROFILE = "staging"
        private const val PRODUCTION_PROFILE = "production"

        /**
         * 環境を判別して返します。
         *
         * @return
         */
        fun get(): Environment {
            // システムプロパティに設定したspringプロファイルを取得する
            val environment = System.getProperty(SPRING_PROFILES_ACTIVE)
            if (LOCAL_PROFILE.equals(environment, ignoreCase = true)) {
                return LOCAL
            } else if (DEV_PROFILE.equals(environment, ignoreCase = true)) {
                return DEV
            } else if (STG_PROFILE.equals(environment, ignoreCase = true)) {
                return STG
            } else if (PRODUCTION_PROFILE.equals(environment, ignoreCase = true)) {
                return PRODUCTION
            }
            return LOCAL
        }

        /**
         * OSがWindowsの場合はtrueを返します。
         *
         * @return
         */
        val isWindowsOs: Boolean
            get() = File.separator == "\\"
    }
}
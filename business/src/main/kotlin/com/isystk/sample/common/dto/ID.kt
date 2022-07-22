package com.isystk.sample.common.dto

import org.seasar.doma.Domain
import java.io.Serializable

/**
 * ID値を内包するクラス
 *
 * @param <T>
</T> */
@Domain(valueType = Int::class, factoryMethod = "of")
class ID<T>
/**
 * コンストラクタ
 *
 * @param value
 */ private constructor(
    /**
     * 内包している値を返します。
     *
     * @return
     */
    val value: Int
) : Serializable {

    companion object {
        private const val serialVersionUID = -8883289947172519834L

        /**
         * ファクトリメソッド
         *
         * @param value
         * @param <R>
         * @return
        </R> */
        @JvmStatic
        fun <R> of(value: Int): ID<R> {
            return ID(value)
        }
    }
}
package com.isystk.sample.common

/**
 * XORShift乱数生成器
 */
class XORShiftRandom @JvmOverloads constructor(private var last: Long = System.currentTimeMillis()) {
    fun nextInt(max: Int): Int {
        last = last xor (last shl 21)
        last = last xor (last ushr 35)
        last = last xor (last shl 4)
        val out = last.toInt() % max
        return if (out < 0) -out else out
    }
}
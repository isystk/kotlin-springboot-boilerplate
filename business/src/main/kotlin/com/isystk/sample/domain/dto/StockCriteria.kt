package com.isystk.sample.domain.dto;

import java.time.LocalDateTime;

/**
 * 商品
 *
 * 自動生成のため原則修正禁止!!
 *
 */
class StockCriteria {


    var idEq: Long? = null
    var idNe: Long? = null
    var idLt: Long? = null
    var idLe: Long? = null
    var idGt: Long? = null
    var idGe: Long? = null
    var idIsNull: Boolean = false
    var idIsNotNull: Boolean = false
    var idIn: List<Long?>? = null
    var idNotIn: List<Long?>? = null

    var nameEq: String? = null
    var nameNe: String? = null
    var nameLt: String? = null
    var nameLe: String? = null
    var nameGt: String? = null
    var nameGe: String? = null
    var nameIsNull: Boolean = false
    var nameIsNotNull: Boolean = false
    var nameIn: List<String?>? = null
    var nameNotIn: List<String?>? = null
    var nameLike: String? = null
    var nameNotLike: String? = null
    var nameStarts: String? = null
    var nameNotStarts: String? = null
    var nameEnds: String? = null
    var nameNotEnds: String? = null

    var detailEq: String? = null
    var detailNe: String? = null
    var detailLt: String? = null
    var detailLe: String? = null
    var detailGt: String? = null
    var detailGe: String? = null
    var detailIsNull: Boolean = false
    var detailIsNotNull: Boolean = false
    var detailIn: List<String?>? = null
    var detailNotIn: List<String?>? = null
    var detailLike: String? = null
    var detailNotLike: String? = null
    var detailStarts: String? = null
    var detailNotStarts: String? = null
    var detailEnds: String? = null
    var detailNotEnds: String? = null

    var priceEq: Long? = null
    var priceNe: Long? = null
    var priceLt: Long? = null
    var priceLe: Long? = null
    var priceGt: Long? = null
    var priceGe: Long? = null
    var priceIsNull: Boolean = false
    var priceIsNotNull: Boolean = false
    var priceIn: List<Long?>? = null
    var priceNotIn: List<Long?>? = null

    var imgpathEq: String? = null
    var imgpathNe: String? = null
    var imgpathLt: String? = null
    var imgpathLe: String? = null
    var imgpathGt: String? = null
    var imgpathGe: String? = null
    var imgpathIsNull: Boolean = false
    var imgpathIsNotNull: Boolean = false
    var imgpathIn: List<String?>? = null
    var imgpathNotIn: List<String?>? = null
    var imgpathLike: String? = null
    var imgpathNotLike: String? = null
    var imgpathStarts: String? = null
    var imgpathNotStarts: String? = null
    var imgpathEnds: String? = null
    var imgpathNotEnds: String? = null

    var quantityEq: Long? = null
    var quantityNe: Long? = null
    var quantityLt: Long? = null
    var quantityLe: Long? = null
    var quantityGt: Long? = null
    var quantityGe: Long? = null
    var quantityIsNull: Boolean = false
    var quantityIsNotNull: Boolean = false
    var quantityIn: List<Long?>? = null
    var quantityNotIn: List<Long?>? = null

    var createdAtEq: LocalDateTime? = null
    var createdAtNe: LocalDateTime? = null
    var createdAtLt: LocalDateTime? = null
    var createdAtLe: LocalDateTime? = null
    var createdAtGt: LocalDateTime? = null
    var createdAtGe: LocalDateTime? = null
    var createdAtIsNull: Boolean = false
    var createdAtIsNotNull: Boolean = false

    var updatedAtEq: LocalDateTime? = null
    var updatedAtNe: LocalDateTime? = null
    var updatedAtLt: LocalDateTime? = null
    var updatedAtLe: LocalDateTime? = null
    var updatedAtGt: LocalDateTime? = null
    var updatedAtGe: LocalDateTime? = null
    var updatedAtIsNull: Boolean = false
    var updatedAtIsNotNull: Boolean = false

    var deleteFlgTrue: Boolean = false
    var deleteFlgFalse: Boolean = false


    var orderBy: String? = null;
}
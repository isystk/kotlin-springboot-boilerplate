package com.isystk.sample.domain.dto;

import java.time.LocalDateTime;

/**
 * 注文履歴
 *
 * 自動生成のため原則修正禁止!!
 *
 */
class OrderHistoryCriteria {


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

    var stockIdEq: Long? = null
    var stockIdNe: Long? = null
    var stockIdLt: Long? = null
    var stockIdLe: Long? = null
    var stockIdGt: Long? = null
    var stockIdGe: Long? = null
    var stockIdIsNull: Boolean = false
    var stockIdIsNotNull: Boolean = false
    var stockIdIn: List<Long?>? = null
    var stockIdNotIn: List<Long?>? = null

    var userIdEq: Long? = null
    var userIdNe: Long? = null
    var userIdLt: Long? = null
    var userIdLe: Long? = null
    var userIdGt: Long? = null
    var userIdGe: Long? = null
    var userIdIsNull: Boolean = false
    var userIdIsNotNull: Boolean = false
    var userIdIn: List<Long?>? = null
    var userIdNotIn: List<Long?>? = null

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
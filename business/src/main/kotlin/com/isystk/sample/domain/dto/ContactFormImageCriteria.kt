package com.isystk.sample.domain.dto;

import java.time.LocalDateTime;

/**
 * お問い合わせ画像
 *
 * 自動生成のため原則修正禁止!!
 *
 */
class ContactFormImageCriteria {


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

    var contactFormIdEq: Long? = null
    var contactFormIdNe: Long? = null
    var contactFormIdLt: Long? = null
    var contactFormIdLe: Long? = null
    var contactFormIdGt: Long? = null
    var contactFormIdGe: Long? = null
    var contactFormIdIsNull: Boolean = false
    var contactFormIdIsNotNull: Boolean = false
    var contactFormIdIn: List<Long?>? = null
    var contactFormIdNotIn: List<Long?>? = null

    var fileNameEq: String? = null
    var fileNameNe: String? = null
    var fileNameLt: String? = null
    var fileNameLe: String? = null
    var fileNameGt: String? = null
    var fileNameGe: String? = null
    var fileNameIsNull: Boolean = false
    var fileNameIsNotNull: Boolean = false
    var fileNameIn: List<String?>? = null
    var fileNameNotIn: List<String?>? = null
    var fileNameLike: String? = null
    var fileNameNotLike: String? = null
    var fileNameStarts: String? = null
    var fileNameNotStarts: String? = null
    var fileNameEnds: String? = null
    var fileNameNotEnds: String? = null

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
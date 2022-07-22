package com.isystk.sample.domain.dto;

import java.time.LocalDateTime;

/**
 * パスワードリセット
 *
 * 自動生成のため原則修正禁止!!
 *
 */
class PasswordResetCriteria {


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

    var emailEq: String? = null
    var emailNe: String? = null
    var emailLt: String? = null
    var emailLe: String? = null
    var emailGt: String? = null
    var emailGe: String? = null
    var emailIsNull: Boolean = false
    var emailIsNotNull: Boolean = false
    var emailIn: List<String?>? = null
    var emailNotIn: List<String?>? = null
    var emailLike: String? = null
    var emailNotLike: String? = null
    var emailStarts: String? = null
    var emailNotStarts: String? = null
    var emailEnds: String? = null
    var emailNotEnds: String? = null

    var tokenEq: String? = null
    var tokenNe: String? = null
    var tokenLt: String? = null
    var tokenLe: String? = null
    var tokenGt: String? = null
    var tokenGe: String? = null
    var tokenIsNull: Boolean = false
    var tokenIsNotNull: Boolean = false
    var tokenIn: List<String?>? = null
    var tokenNotIn: List<String?>? = null
    var tokenLike: String? = null
    var tokenNotLike: String? = null
    var tokenStarts: String? = null
    var tokenNotStarts: String? = null
    var tokenEnds: String? = null
    var tokenNotEnds: String? = null

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
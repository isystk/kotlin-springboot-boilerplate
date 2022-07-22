package com.isystk.sample.domain.dto;

import java.time.LocalDateTime;

/**
 * メールテンプレート
 *
 * 自動生成のため原則修正禁止!!
 *
 */
class MailTemplateCriteria {


    var mailTemplateIdEq: Long? = null
    var mailTemplateIdNe: Long? = null
    var mailTemplateIdLt: Long? = null
    var mailTemplateIdLe: Long? = null
    var mailTemplateIdGt: Long? = null
    var mailTemplateIdGe: Long? = null
    var mailTemplateIdIsNull: Boolean = false
    var mailTemplateIdIsNotNull: Boolean = false
    var mailTemplateIdIn: List<Long?>? = null
    var mailTemplateIdNotIn: List<Long?>? = null

    var mailTemplateDivEq: Short? = null
    var mailTemplateDivNe: Short? = null
    var mailTemplateDivLt: Short? = null
    var mailTemplateDivLe: Short? = null
    var mailTemplateDivGt: Short? = null
    var mailTemplateDivGe: Short? = null
    var mailTemplateDivIsNull: Boolean = false
    var mailTemplateDivIsNotNull: Boolean = false
    var mailTemplateDivIn: List<Short?>? = null
    var mailTemplateDivNotIn: List<Short?>? = null

    var titleEq: String? = null
    var titleNe: String? = null
    var titleLt: String? = null
    var titleLe: String? = null
    var titleGt: String? = null
    var titleGe: String? = null
    var titleIsNull: Boolean = false
    var titleIsNotNull: Boolean = false
    var titleIn: List<String?>? = null
    var titleNotIn: List<String?>? = null
    var titleLike: String? = null
    var titleNotLike: String? = null
    var titleStarts: String? = null
    var titleNotStarts: String? = null
    var titleEnds: String? = null
    var titleNotEnds: String? = null

    var textEq: String? = null
    var textNe: String? = null
    var textLt: String? = null
    var textLe: String? = null
    var textGt: String? = null
    var textGe: String? = null
    var textIsNull: Boolean = false
    var textIsNotNull: Boolean = false
    var textIn: List<String?>? = null
    var textNotIn: List<String?>? = null
    var textLike: String? = null
    var textNotLike: String? = null
    var textStarts: String? = null
    var textNotStarts: String? = null
    var textEnds: String? = null
    var textNotEnds: String? = null

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
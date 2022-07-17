package com.isystk.sample.web.base.controller.api.resource

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class FieldErrorResource {
    // 項目名
    var fieldName: String? = null

    // エラー種別
    var errorType: String? = null

    // エラーメッセージ
    var errorMessage: String? = null
    override fun equals(o: Any?): Boolean {
        if (o === this) {
            return true
        }
        if (o !is FieldErrorResource) {
            return false
        }
        val other = o
        if (!other.canEqual(this as Any)) {
            return false
        }
        val `this$fieldName`: Any? = fieldName
        val `other$fieldName`: Any? = other.fieldName
        if (if (`this$fieldName` == null) `other$fieldName` != null else `this$fieldName` != `other$fieldName`) {
            return false
        }
        val `this$errorType`: Any? = errorType
        val `other$errorType`: Any? = other.errorType
        if (if (`this$errorType` == null) `other$errorType` != null else `this$errorType` != `other$errorType`) {
            return false
        }
        val `this$errorMessage`: Any? = errorMessage
        val `other$errorMessage`: Any? = other.errorMessage
        return if (if (`this$errorMessage` == null) `other$errorMessage` != null else `this$errorMessage` != `other$errorMessage`) {
            false
        } else true
    }

    protected fun canEqual(other: Any?): Boolean {
        return other is FieldErrorResource
    }

    override fun hashCode(): Int {
        val PRIME = 59
        var result = 1
        val `$fieldName`: Any? = fieldName
        result = result * PRIME + (`$fieldName`?.hashCode() ?: 43)
        val `$errorType`: Any? = errorType
        result = result * PRIME + (`$errorType`?.hashCode() ?: 43)
        val `$errorMessage`: Any? = errorMessage
        result = result * PRIME + (`$errorMessage`?.hashCode() ?: 43)
        return result
    }

    override fun toString(): String {
        return ("FieldErrorResource(fieldName=" + fieldName + ", errorType="
                + errorType + ", errorMessage=" + errorMessage + ")")
    }
}
package com.isystk.sample.web.base.controller.api.resource

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
class ErrorResourceImpl : ResourceImpl() {
    // リクエストID
    var requestId: String? = null

    // 入力エラー
    var fieldErrors: List<FieldErrorResource>? = null
    override fun equals(o: Any?): Boolean {
        if (o === this) {
            return true
        }
        if (o !is ErrorResourceImpl) {
            return false
        }
        val other = o
        if (!other.canEqual(this as Any)) {
            return false
        }
        if (!super.equals(o)) {
            return false
        }
        val `this$requestId`: Any? = requestId
        val `other$requestId`: Any? = other.requestId
        if (if (`this$requestId` == null) `other$requestId` != null else `this$requestId` != `other$requestId`) {
            return false
        }
        val `this$fieldErrors`: Any? = fieldErrors
        val `other$fieldErrors`: Any? = other.fieldErrors
        return if (if (`this$fieldErrors` == null) `other$fieldErrors` != null else `this$fieldErrors` != `other$fieldErrors`) {
            false
        } else true
    }

    override fun canEqual(other: Any?): Boolean {
        return other is ErrorResourceImpl
    }

    override fun hashCode(): Int {
        val PRIME = 59
        var result = super.hashCode()
        val `$requestId`: Any? = requestId
        result = result * PRIME + (`$requestId`?.hashCode() ?: 43)
        val `$fieldErrors`: Any? = fieldErrors
        result = result * PRIME + (`$fieldErrors`?.hashCode() ?: 43)
        return result
    }
}
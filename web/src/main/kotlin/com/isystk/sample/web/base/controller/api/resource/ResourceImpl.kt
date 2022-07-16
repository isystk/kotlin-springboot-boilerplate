package com.isystk.sample.web.base.controller.api.resource

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.isystk.sample.common.dto.Dto

@JsonIgnoreProperties(ignoreUnknown = true)
open class ResourceImpl : Resource {
    override var data: List<Dto?>? = null

    // メッセージ
    override var message: String? = null
    override var result: Boolean? = null
    override fun equals(o: Any?): Boolean {
        if (o === this) {
            return true
        }
        if (o !is ResourceImpl) {
            return false
        }
        val other = o
        if (!other.canEqual(this as Any)) {
            return false
        }
        val `this$data`: Any? = data
        val `other$data`: Any? = other.data
        if (if (`this$data` == null) `other$data` != null else `this$data` != `other$data`) {
            return false
        }
        val `this$message`: Any? = message
        val `other$message`: Any? = other.message
        if (if (`this$message` == null) `other$message` != null else `this$message` != `other$message`) {
            return false
        }
        val `this$result`: Any? = result
        val `other$result`: Any? = other.result
        return if (if (`this$result` == null) `other$result` != null else `this$result` != `other$result`) {
            false
        } else true
    }

    protected open fun canEqual(other: Any?): Boolean {
        return other is ResourceImpl
    }

    override fun hashCode(): Int {
        val PRIME = 59
        var result = 1
        val `$data`: Any? = data
        result = result * PRIME + (`$data`?.hashCode() ?: 43)
        val `$message`: Any? = message
        result = result * PRIME + (`$message`?.hashCode() ?: 43)
        val `$result`: Any? = this.result
        result = result * PRIME + (`$result`?.hashCode() ?: 43)
        return result
    }

    override fun toString(): String {
        return ("ResourceImpl(data=" + data + ", message=" + message + ", result="
                + result + ")")
    }
}
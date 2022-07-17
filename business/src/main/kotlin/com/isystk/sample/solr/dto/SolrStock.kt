package com.isystk.sample.solr.dto

import com.isystk.sample.common.dto.Dto
import org.apache.solr.client.solrj.beans.Field
import java.util.*

open class SolrStock : Dto {
    @Field("id")
    var id: String? = null

    @Field("stock_id")
    var stockId: Int? = null

    @Field("name")
    var name: String? = null

    @Field("detail")
    var detail: String? = null

    @Field("price")
    var price: Int? = null

    @Field("imgpath")
    var imgpath: String? = null

    @Field("quantity")
    var quantity: Int? = null

    @Field("created_at")
    var createdAtDate: Date? = null
    override fun equals(o: Any?): Boolean {
        if (o === this) {
            return true
        }
        if (o !is SolrStock) {
            return false
        }
        val other = o
        if (!other.canEqual(this as Any)) {
            return false
        }
        val `this$id`: Any? = id
        val `other$id`: Any? = other.id
        if (if (`this$id` == null) `other$id` != null else `this$id` != `other$id`) {
            return false
        }
        val `this$stockId`: Any? = stockId
        val `other$stockId`: Any? = other.stockId
        if (if (`this$stockId` == null) `other$stockId` != null else `this$stockId` != `other$stockId`) {
            return false
        }
        val `this$name`: Any? = name
        val `other$name`: Any? = other.name
        if (if (`this$name` == null) `other$name` != null else `this$name` != `other$name`) {
            return false
        }
        val `this$detail`: Any? = detail
        val `other$detail`: Any? = other.detail
        if (if (`this$detail` == null) `other$detail` != null else `this$detail` != `other$detail`) {
            return false
        }
        val `this$price`: Any? = price
        val `other$price`: Any? = other.price
        if (if (`this$price` == null) `other$price` != null else `this$price` != `other$price`) {
            return false
        }
        val `this$imgpath`: Any? = imgpath
        val `other$imgpath`: Any? = other.imgpath
        if (if (`this$imgpath` == null) `other$imgpath` != null else `this$imgpath` != `other$imgpath`) {
            return false
        }
        val `this$quantity`: Any? = quantity
        val `other$quantity`: Any? = other.quantity
        if (if (`this$quantity` == null) `other$quantity` != null else `this$quantity` != `other$quantity`) {
            return false
        }
        val `this$createdAtDate`: Any? = createdAtDate
        val `other$createdAtDate`: Any? = other.createdAtDate
        return if (if (`this$createdAtDate` == null) `other$createdAtDate` != null else `this$createdAtDate` != `other$createdAtDate`) {
            false
        } else true
    }

    protected fun canEqual(other: Any?): Boolean {
        return other is SolrStock
    }

    override fun hashCode(): Int {
        val PRIME = 59
        var result = 1
        val `$id`: Any? = id
        result = result * PRIME + (`$id`?.hashCode() ?: 43)
        val `$stockId`: Any? = stockId
        result = result * PRIME + (`$stockId`?.hashCode() ?: 43)
        val `$name`: Any? = name
        result = result * PRIME + (`$name`?.hashCode() ?: 43)
        val `$detail`: Any? = detail
        result = result * PRIME + (`$detail`?.hashCode() ?: 43)
        val `$price`: Any? = price
        result = result * PRIME + (`$price`?.hashCode() ?: 43)
        val `$imgpath`: Any? = imgpath
        result = result * PRIME + (`$imgpath`?.hashCode() ?: 43)
        val `$quantity`: Any? = quantity
        result = result * PRIME + (`$quantity`?.hashCode() ?: 43)
        val `$createdAtDate`: Any? = createdAtDate
        result = result * PRIME + (`$createdAtDate`?.hashCode() ?: 43)
        return result
    }

    override fun toString(): String {
        return ("SolrStock(id=" + id + ", stockId=" + stockId + ", name="
                + name + ", detail=" + detail + ", price=" + price
                + ", imgpath=" + imgpath + ", quantity=" + quantity + ", createdAtDate="
                + createdAtDate + ")")
    }
}
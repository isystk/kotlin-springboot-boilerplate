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

}
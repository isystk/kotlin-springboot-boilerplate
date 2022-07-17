package com.isystk.sample.solr

import org.apache.solr.client.solrj.impl.HttpSolrClient
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean

open class BaseSolrConfig {
    @Value("#{'\${spring.data.solr.host:*}'}")
    var solrHost: String? = null
    @Bean
    open fun stockSolrClient(): HttpSolrClient {
        return HttpSolrClient.Builder("$solrHost/stock").build()
    }
}
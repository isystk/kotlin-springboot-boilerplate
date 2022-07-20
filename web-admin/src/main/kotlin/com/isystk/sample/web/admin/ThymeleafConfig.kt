package com.isystk.sample.web.admin

import com.isystk.sample.web.base.thymeleaf.CustomDialect
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ThymeleafConfig {
    @Bean
    fun layoutDialect(): LayoutDialect {
        return LayoutDialect()
    }

    @Bean
    fun myDialect(): CustomDialect {
        return CustomDialect()
    }
}
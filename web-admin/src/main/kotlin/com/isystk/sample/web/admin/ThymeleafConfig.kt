package com.isystk.sample.web.admin

import com.isystk.sample.web.base.thymeleaf.CustomDialect
import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ThymeleafConfig {
    @Bean
    open fun layoutDialect(): LayoutDialect {
        return LayoutDialect()
    }

    @Bean
    open fun myDialect(): CustomDialect {
        return CustomDialect()
    }
}
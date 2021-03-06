package com.isystk.sample.web.admin

import com.isystk.sample.web.base.thymeleaf.CustomDialect
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ThymeleafConfiguration {
    @Bean
    fun myDialect(): CustomDialect {
        return CustomDialect()
    }
}
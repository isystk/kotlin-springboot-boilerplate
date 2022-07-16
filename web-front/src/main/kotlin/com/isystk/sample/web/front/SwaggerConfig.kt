package com.isystk.sample.web.front

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket

@Configuration
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
                .select() //        // パッケージを指定して表示するAPIを絞り込む
                //        .apis(RequestHandlerSelectors.basePackage("com.isystk.sample.web.front.controller.api.v1"))
                // パスを指定して絞り込む
                .paths(PathSelectors.ant("/api/v1/**"))
                .build()
                .protocols(setOf("https"))
                .host("localhost")
                .apiInfo(apiInfo())
    }

    private fun apiInfo(): ApiInfo {
        return ApiInfoBuilder() // タイトル
                .title("LaraEC API") // 詳細な説明
                .description("LaraECで利用されているAPIの一覧です") // バージョン
                .version("1.0.0") // 連絡先
                .contact(Contact("isystk", "https://profile.isystk.com", "iseyoshitaka@isystk.com")) // ライセンス名
                .license("isystk") // ライセンスURL
                .licenseUrl("https://github.com/isystk/java-springboot-boilerplate/blob/master/LICENSE") //        // 利用規約のURL
                //        .termsOfServiceUrl("http://example.com/termsofuse")
                .build()
    }
}
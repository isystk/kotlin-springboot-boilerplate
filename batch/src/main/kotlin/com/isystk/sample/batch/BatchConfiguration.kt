package com.isystk.sample.batch

import com.isystk.sample.batch.jobs.SingleJobCommandLineRunner
import com.isystk.sample.common.dto.DefaultPageFactoryImpl
import com.isystk.sample.common.dto.PageFactory
import com.isystk.sample.common.util.MessageUtils
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.converter.JobParametersConverter
import org.springframework.batch.core.jsr.JsrJobParametersConverter
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean
import javax.sql.DataSource

@Configuration
@EnableBatchProcessing
class BatchConfiguration : InitializingBean {
    @Value("\${application.batch.corePoolSize:2}")
    var corePoolSize = 2

    @Value("\${application.batch.maxPoolSize:64}")
    var maxPoolSize = 64

    @Autowired
    var messageSource: MessageSource? = null

    @Bean
    fun jobParametersConverter(dataSource: DataSource?): JobParametersConverter {
        return JsrJobParametersConverter(dataSource!!)
    }

    @Bean
    fun commandLineRunner(): SingleJobCommandLineRunner {
        return SingleJobCommandLineRunner()
    }

    @Bean
    fun taskExecutor(): ThreadPoolTaskExecutor {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = corePoolSize
        executor.maxPoolSize = maxPoolSize
        executor.setWaitForTasksToCompleteOnShutdown(true)
        return executor
    }

    @Bean
    fun beanValidator(messageSource: MessageSource?): LocalValidatorFactoryBean {
        val bean = LocalValidatorFactoryBean()
        bean.setValidationMessageSource(messageSource!!)
        return bean
    }

    @Bean
    fun pageFactory(): PageFactory {
        return DefaultPageFactoryImpl()
    }

    override fun afterPropertiesSet() {
        MessageUtils().setMessageSource(messageSource)
    }
}
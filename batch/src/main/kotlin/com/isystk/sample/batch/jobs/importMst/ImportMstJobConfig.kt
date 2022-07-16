package com.isystk.sample.batch.jobs.importMst

import com.isystk.sample.batch.listener.DefaultStepExecutionListener
import org.springframework.batch.core.Job
import org.springframework.batch.core.JobExecutionListener
import org.springframework.batch.core.Step
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory
import org.springframework.batch.core.launch.support.RunIdIncrementer
import org.springframework.batch.core.step.tasklet.Tasklet
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 * マスタ取り込み
 */
@Configuration
@EnableBatchProcessing
class ImportMstJobConfig {
    @Autowired
    var jobBuilderFactory: JobBuilderFactory? = null

    @Autowired
    var stepBuilderFactory: StepBuilderFactory? = null
    @Bean
    fun importUserJobListener(): JobExecutionListener {
        return ImportMstJobListener()
    }

    @Bean
    fun importMstJob(): Job {
        return jobBuilderFactory!!["importMstJob"].incrementer(RunIdIncrementer())
                .listener(importUserJobListener())
                .start(importMstStockStep()) //       .next(importMstPostStep())
                .build()
    }

    @Bean
    fun importMstStockStep(): Step {
        return stepBuilderFactory!!["importMstStockStep"].listener(DefaultStepExecutionListener())
                .tasklet(importMstStockTasklet()).build()
    }

    @Bean
    fun importMstStockTasklet(): Tasklet {
        return ImportMstStockTasklet()
    }
}
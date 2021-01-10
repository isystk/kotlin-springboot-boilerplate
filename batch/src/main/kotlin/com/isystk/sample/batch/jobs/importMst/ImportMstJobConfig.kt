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
 * ユーザー情報取り込み
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
                .listener(importUserJobListener()).start(importMstPostStep()) //              .next(importMstPostStep())
                .build()
    }

    @Bean
    fun importMstPostStep(): Step {
        return stepBuilderFactory!!["importMstPostStep"].listener(DefaultStepExecutionListener())
                .tasklet(importMstPostTasklet()).build()
    }

    @Bean
    fun importMstPostTasklet(): Tasklet {
        return ImportMstPostTasklet()
    }
}
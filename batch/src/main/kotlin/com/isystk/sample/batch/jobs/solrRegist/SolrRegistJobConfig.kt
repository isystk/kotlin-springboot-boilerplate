package com.isystk.sample.batch.jobs.solrRegist

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
 * インデックス生成
 */
@Configuration
@EnableBatchProcessing
class SolrRegistJobConfig {
    @Autowired
    var jobBuilderFactory: JobBuilderFactory? = null

    @Autowired
    var stepBuilderFactory: StepBuilderFactory? = null

    @Bean
    fun solrlRegistJobListener(): JobExecutionListener {
        return SolrlRegistJobListener()
    }

    @Bean
    fun solrRegistJob(): Job {
        return jobBuilderFactory!!["solrRegistJob"].incrementer(RunIdIncrementer())
                .listener(solrlRegistJobListener()).start(solrRegistStep1()) //                .next(solrRegistStep2())
                .build()
    }

    @Bean
    fun solrRegistStep1(): Step {
        return stepBuilderFactory!!["solrRegistStep1"].listener(DefaultStepExecutionListener())
                .tasklet(solrRegistTasklet()).build()
    }

    @Bean
    fun solrRegistStep2(): Step {
        return stepBuilderFactory!!["solrRegistStep2"].listener(DefaultStepExecutionListener())
                .tasklet(solrRegistTasklet()).build()
    }

    @Bean
    fun solrRegistTasklet(): Tasklet {
        return SolrRegistTasklet()
    }
}
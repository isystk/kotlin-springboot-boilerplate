package com.isystk.sample.batch

import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType
import com.isystk.sample.ComponentScanBasePackage
import org.slf4j.LoggerFactory
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackageClasses = [ComponentScanBasePackage::class])
class Application

private val logger = LoggerFactory.getLogger(Application::class.java)

fun main(args: Array<String>) {
    try {
        val application = SpringApplication(Application::class.java)
        application.webApplicationType = WebApplicationType.NONE
        val context = application.run(*args)
        val exitCode = SpringApplication.exit(context)
        System.exit(exitCode)
    } catch (t: Throwable) {
        logger.error("failed to run. ", t)
        System.exit(1)
    }
}

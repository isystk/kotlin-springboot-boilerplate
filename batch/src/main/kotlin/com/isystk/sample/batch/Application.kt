package com.isystk.sample.batch

import org.slf4j.LoggerFactory
import org.springframework.boot.SpringApplication
import org.springframework.boot.WebApplicationType

object Application {
    private val log = LoggerFactory.getLogger(Application::class.java)

    @Throws(Exception::class)
    @JvmStatic
    fun main(args: Array<String>) {
        try {
            val application = SpringApplication(Application::class.java)
            application.webApplicationType = WebApplicationType.NONE
            val context = application.run(*args)
            val exitCode = SpringApplication.exit(context)
            System.exit(exitCode)
        } catch (t: Throwable) {
            log.error("failed to run. ", t)
            System.exit(1)
        }
    }
}
package com.isystk.sample.web.front

import com.isystk.sample.ComponentScanBasePackage
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.SpringApplication

@SpringBootApplication(scanBasePackageClasses = [ComponentScanBasePackage::class])
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
package com.isystk.sample.batch

import org.springframework.boot.SpringApplication
import com.isystk.sample.ComponentScanBasePackage
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication(scanBasePackageClasses = [ComponentScanBasePackage::class])
class Application

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}
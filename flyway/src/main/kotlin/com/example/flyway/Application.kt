package com.example.flyway

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class Application : CommandLineRunner {
    override fun run(vararg args: String) {
        val logger = LoggerFactory.getLogger(this::class.java)
        logger.info("Migrate処理を開始します")
        logger.info("Migrate処理を終了します")
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
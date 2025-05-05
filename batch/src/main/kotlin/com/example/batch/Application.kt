package com.example.batch

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.ExitCodeGenerator
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication
class Application(
    private val jdbcTemplate: JdbcTemplate
) : CommandLineRunner, ExitCodeGenerator {
    @Throws(Exception::class)
    override fun run(vararg strings: String?) {
        val logger = LoggerFactory.getLogger(this::class.java)
        logger.info("バッチ処理を開始します")

        jdbcTemplate.execute("DROP TABLE customers IF EXISTS")
        jdbcTemplate.execute(
            "CREATE TABLE customers(" +
                    "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))"
        )

        logger.info("バッチ処理が完了しました")
    }

    override fun getExitCode(): Int {
        return 0
    }
}

fun main(args: Array<String>) {
    SpringApplication.exit(SpringApplication.run(Application::class.java, *args))
}

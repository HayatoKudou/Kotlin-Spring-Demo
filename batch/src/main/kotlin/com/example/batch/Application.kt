package com.example.batch

import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.ExitCodeGenerator
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication
class Application(
    private val jdbcTemplate: JdbcTemplate
) : CommandLineRunner, ExitCodeGenerator {
    override fun run(vararg strings: String?) {
        val logger = LoggerFactory.getLogger(this::class.java)
        logger.info("バッチ処理を開始します")

        jdbcTemplate.execute("DROP TABLE IF EXISTS customers")
        jdbcTemplate.execute(
            "CREATE TABLE customers(" +
                    "id SERIAL, first_name VARCHAR(255), last_name VARCHAR(255))"
        )

        val splitUpNames = listOf("John Woo", "Jeff Dean", "Josh Bloch", "Josh Long")
            .map { it.split(" ") }

        splitUpNames.forEach { (first, last) ->
            logger.info("Inserting customer record for $first $last")
        }

        jdbcTemplate.batchUpdate("INSERT INTO customers(first_name, last_name) VALUES (?,?)",
            splitUpNames.map { it.toTypedArray() })

        jdbcTemplate.query(
            "SELECT id, first_name, last_name FROM customers WHERE first_name = ?",
            { rs, _ ->
                Customer(
                    rs.getLong("id"),
                    rs.getString("first_name"),
                    rs.getString("last_name")
                )
            },
            "Josh"
        ).forEach { customer ->
            logger.info(customer.toString())
        }

        logger.info("バッチ処理が完了しました")
    }

    override fun getExitCode(): Int {
        return 0
    }
}

fun main(args: Array<String>) {
    SpringApplication.exit(SpringApplication.run(Application::class.java, *args))
}

class Customer(private val id: Long, private val firstName: String?, private val lastName: String?) {
    override fun toString(): String {
        return String.format(
            "Customer[id=%d, firstName='%s', lastName='%s']",
            id, firstName, lastName
        )
    }
}
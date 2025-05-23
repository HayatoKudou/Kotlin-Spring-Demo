package com.example.api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(scanBasePackages = ["com.example.api"])
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

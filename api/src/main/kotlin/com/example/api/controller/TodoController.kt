package com.example.api.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong

data class Todo(val id: Long, val content: String?)

@RestController
class TodoController {
    private val counter = AtomicLong()

    @GetMapping("/todos")
    fun get(@RequestParam(value = "name", defaultValue = "World") name: String?): Todo? {
        return Todo(counter.incrementAndGet(), "Hello, $name!")
    }
}
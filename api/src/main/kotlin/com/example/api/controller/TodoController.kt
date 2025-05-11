package com.example.api.controller

import com.example.api.dao.todo.TodoDTO
import com.example.api.dao.todo.TodoDao
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicLong
import com.example.api.repository.TodoRepository
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@RestController
class TodoController(private val todoDao: TodoDao) {

    @GetMapping("/todos")
    fun get(): ResponseEntity<List<TodoDTO>> {
        val todos = todoDao.findAll()
        return ResponseEntity(todos, HttpStatus.OK)
    }
}
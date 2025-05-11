package com.example.api.controller

import com.example.api.dao.todo.TodoDTO
import com.example.api.dao.todo.TodoDAO
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

const val URL_BASE = "/api/v1"

@RestController
@RequestMapping(path = [URL_BASE])
class TodoController(private val todoDao: TodoDAO) {

    @RequestMapping(
        path = ["/todos"],
        method = [RequestMethod.GET],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun get(): ResponseEntity<List<TodoDTO>> {
        val todos = todoDao.findAll()
        return ResponseEntity(todos, HttpStatus.OK)
    }
}
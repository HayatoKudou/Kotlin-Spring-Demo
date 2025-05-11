package com.example.api.dao.todo

import com.example.api.repository.TodoRepository
import org.springframework.stereotype.Component

@Component
class TodoDAO(
    private val todoRepository: TodoRepository
) {
    fun findAll(): List<TodoDTO> {
        val entities = todoRepository.findAll()
        return entities.map(TodoDTO::from)
    }
}
package com.example.api.dao.todo

import com.example.api.entity.ITodoEntity

interface ITodoDTO {
    val id: Long
    val title: String
    val description: String?
    val isCompleted: Boolean
    val dueDate: java.time.LocalDate?
    val createdAt: java.time.OffsetDateTime
    val updatedAt: java.time.OffsetDateTime
}

data class TodoDTO(
    override val id: Long,
    override val title: String,
    override val description: String?,
    override val isCompleted: Boolean,
    override val dueDate: java.time.LocalDate?,
    override val createdAt: java.time.OffsetDateTime,
    override val updatedAt: java.time.OffsetDateTime
) : ITodoDTO {
    companion object {
        internal fun from(entity: ITodoEntity): TodoDTO {
            return TodoDTO(
                id = entity.id,
                title = entity.title,
                description = entity.description,
                isCompleted = entity.isCompleted,
                dueDate = entity.dueDate,
                createdAt = entity.createdAt,
                updatedAt = entity.updatedAt
            )
        }
    }
}
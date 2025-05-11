package com.example.api.entity

import jakarta.persistence.*
import java.time.LocalDate
import java.time.OffsetDateTime

sealed interface ITodoEntity {
    val id: Long
    val title: String
    val description: String?
    val isCompleted: Boolean
    val dueDate: LocalDate?
    val createdAt: OffsetDateTime
    val updatedAt: OffsetDateTime
}

@Entity
@Table(name = "todos")
data class TodoEntity(
    @Id
    override val id: Long,
    override val title: String,
    override val description: String?,
    override val isCompleted: Boolean,
    override val dueDate: LocalDate?,
    override val createdAt: OffsetDateTime,
    override val updatedAt: OffsetDateTime
): ITodoEntity
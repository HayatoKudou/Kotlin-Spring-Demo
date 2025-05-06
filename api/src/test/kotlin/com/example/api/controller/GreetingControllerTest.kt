package com.example.api.controller

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get

@SpringBootTest
@AutoConfigureMockMvc       // MockMvc を自動設定
class GreetingControllerIntegrationTest(
    @Autowired private val mockMvc: MockMvc
) {
    @Test
    @DisplayName("name パラメータを省略した場合は \"Hello, World!\" を返す")
    fun greeting_defaultWorld() {
        mockMvc.get("/greeting")
            .andExpect {
                status { isOk() }
                jsonPath("$.content") { value("Hello, World!") }
                jsonPath("$.id") { isNumber() }
            }
    }

    @Test
    @DisplayName("name=Kotlin を指定すると \"Hello, Kotlin!\" を返す")
    fun greeting_withName() {
        mockMvc.get("/greeting") { param("name", "Kotlin") }
            .andExpect {
                status { isOk() }
                jsonPath("$.content") { value("Hello, Kotlin!") }
                jsonPath("$.id") { isNumber() }
            }
    }
}
package com.study.peoplefund.web

import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.study.peoplefund.service.ProductService
import com.study.peoplefund.web.dto.ProductRequest
import com.study.peoplefund.web.dto.ProductResponse
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status


@WebMvcTest
class ControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    lateinit var productService: ProductService

    val objectMapper = jacksonObjectMapper().registerModule(KotlinModule())

    @Test
    fun `상품 등록`() {
        val request = ProductRequest(
                name = "담보채권",
                price = 100_000_000L
        )

        val requestJson = objectMapper.writeValueAsString(request)

        `when`(productService.register(request)).thenReturn(1L)

        mockMvc.perform(post("/api/products")
                .content(requestJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated)
                .andDo(print())
    }

    @Test
    fun `목록 조회`() {
        val response = listOf(
                ProductResponse(id = 1L, name = "담보 채권", price = 100_000_000),
                ProductResponse(id = 2L, name = "개인 채권", price = 10_000_000)
        )

        `when`(productService.list()).thenReturn(response)

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(response)))
                .andDo(print())
    }

    @Test
    fun `단건 조회`() {
        val response = ProductResponse(id = 1L, name = "담보 채권", price = 100_000_000)

        `when`(productService.detail(1L)).thenReturn(
                ProductResponse(id = 1L, name = "담보 채권", price = 100_000_000)
        )

        mockMvc.perform(get("/api/products/1"))
                .andExpect(status().isOk)
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json(objectMapper.writeValueAsString(response)))
                .andDo(print())
    }
}
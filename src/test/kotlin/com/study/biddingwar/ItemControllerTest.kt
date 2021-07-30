package com.study.biddingwar

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest
class ItemControllerTest(@Autowired val mockMvc: MockMvc) {

    @MockBean
    lateinit var service: ItemService

    @Test
    fun `상품 목록 조회`() {
        mockMvc.perform(get("/item"))
            .andExpect(status().isOk)
            .andExpect(content().json("[]"))
    }
}
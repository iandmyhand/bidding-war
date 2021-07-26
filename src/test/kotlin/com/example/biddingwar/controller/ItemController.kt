package com.example.biddingwar.controller
//import com.example.biddingwar.controller.ItemController

import com.example.biddingwar.dto.ItemCreateForm
import com.example.biddingwar.entity.Item
import com.example.biddingwar.repository.ItemRepository
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.aspectj.lang.annotation.Before
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.AfterTestExecutionCallback
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.api.extension.ExtensionContext
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post
import org.springframework.boot.test.web.client.getForObject as getForObject
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import java.time.LocalDateTime

//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class ItemControllerTests{
//    @Test
//    fun contextLoads(){
//
//    }
//}

@SpringBootTest
class Tests(@Autowired val itemRepository: ItemRepository){
    @Test
    fun `상품 등록`() {
        val request = ItemCreateForm(
            productName = "갤럭시 노트10",
            title="스마트폰 팔아여",
            content="대략 2년정도 사용했네요",
            price = 400_000
        )

        itemRepository.save(request.toEntity())
        val response = itemRepository.findById(1)

        assertThat(response)
            .isEqualTo(Item(1, "갤럭시 노트10", "스마트폰 팔아여", "대략 2년정도 사용했네요", 400_000))
    }

    @Test
    fun `상품 조회`(){
        val request1 = ItemCreateForm(
            productName = "갤럭시 노트10",
            title="스마트폰 팔아여",
            content="대략 2년정도 사용했네요",
            price = 400_000
        )
        val request2 = ItemCreateForm(
            productName = "갤럭시 노트 10 5G",
            title="갤럭시 노트 10 팔아여",
            content="대략 1년정도 사용했네요",
            price = 200_000
        )

        itemRepository.save(request1.toEntity())
        itemRepository.save(request2.toEntity())
        val response = itemRepository.findAllByProductNameContainingOrderByCreatedTimeDesc("갤럭시 노트")

        assertThat(response).first()
            .isEqualTo(Item(1, "갤럭시 노트10", "스마트폰 팔아여", "대략 2년정도 사용했네요", 400_000))
        assertThat(response).last()
            .isEqualTo(Item(2, "갤럭시 노트10 5G", "갤럭시 노트 10 팔아여", "대략 1년정도 사용했네요", 200_000))
    }
}

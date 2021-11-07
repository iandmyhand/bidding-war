package com.study.biddingwar


import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest


@WebMvcTest
class ItemControllerTest {

//    @TestConfiguration
//    class ItemControllerTestConfig {
//        @Bean
//        fun service() = mockk<ItemService>()
//    }
//
//    @Autowired
//    private lateinit var mvc: MockMvc
//
//    @Autowired
//    private lateinit var service: ItemService
//
//    private val mapper = jacksonObjectMapper()
//        .registerModule(JavaTimeModule())
//        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
//
//    @Test
//    fun `상품 목록 조회`() {
//
//        val itemList = listOf(
//            Item(
//                id=1,
//                seller="승완",
//                title="똥묻은변기",
//                initialPrice=100000,
//                category="생활용품",
//                description="아들의 똥이 묻은 하나뿐인 변기"
//            ),
//        )
//
//        every { service.list() } returns itemList
//
//        val serialized = mapper.writeValueAsString(itemList)
//
//        mvc.perform(get("/item"))
//            .andExpect(status().isOk)
//            .andExpect(content().json(serialized))
//    }
}
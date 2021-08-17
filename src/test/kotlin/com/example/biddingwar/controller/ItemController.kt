package com.example.biddingwar.controller
//import com.example.biddingwar.controller.ItemController

import com.example.biddingwar.dto.ItemCreateForm
import com.example.biddingwar.entity.Item
import com.example.biddingwar.repository.ItemRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.LocalDateTime

@SpringBootTest
class Tests(@Autowired val itemRepository: ItemRepository){
    @Test
    fun `상품 등록`() {
        var now = LocalDateTime.now()

        val request = ItemCreateForm(
            productName = "갤럭시 노트10",
            title="스마트폰 팔아여",
            content="대략 2년정도 사용했네요",
            price = 400_000,
            createTime = now
        )

        itemRepository.save(request.toEntity())
        val response = itemRepository.findById(1).get()
        val expected = Item(1, "갤럭시 노트10",
            "스마트폰 팔아여", "대략 2년정도 사용했네요", 400_000, now)

        assertThat(response.toString())
            .isEqualTo(expected.toString())
    }

    @Test
    fun `상품 조회`(){
        var now = LocalDateTime.now()

        val request1 = ItemCreateForm(
            productName = "갤럭시 노트10",
            title="스마트폰 팔아여",
            content="대략 2년정도 사용했네요",
            price = 400_000,
            createTime = now
        )
        val request2 = ItemCreateForm(
            productName = "갤럭시 노트 10 5G",
            title="갤럭시 노트 10 팔아여",
            content="대략 1년정도 사용했네요",
            price = 200_000,
            createTime = now
        )

        itemRepository.save(request1.toEntity())
        itemRepository.save(request2.toEntity())
        val response = itemRepository.findAllByProductNameContainingOrderByCreatedTimeDesc("갤럭시 노트")

        val expected1 = Item(1, "갤럭시 노트10", "스마트폰 팔아여", "대략 2년정도 사용했네요", 400_000, now);
        val expected2 = Item(2, "갤럭시 노트10 5G", "갤럭시 노트 10 팔아여", "대략 1년정도 사용했네요", 200_000, now)

        assertThat(response.first().toString())
            .isEqualTo(expected1.toString())
        assertThat(response.last().toString())
            .isEqualTo(expected2.toString())
    }
}


//
//class LocalDateTimeExtension : AfterTestExecutionCallback {
//    override fun afterTestExecution(context: ExtensionContext) {
//        // 테스트가 끝날 때 마다 현재시각으로 돌린다.
//        LocalDateTimeTestHelper.unfixCurrentDateTime()
//    }
//}


//        val requestJson = objectMapper.writeValueAsString(request)
//        val requestJson = objectMapper.writeValueAsString(ItemCreateForm("갤럭시 노트10", "smartphone", " 2years", 40000))
//        mockMvc.perform(post("/item/create")
//            .content(requestJson)
//        ).andDo(print())
//            .andExpect(status().isOk)


//
//    @Test
//    fun first(){
//        mockMvc.perform()
//
//    }





//@SpringBootTest(
//    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
//    properties = [
//        "spring.datasource.url=jbdc::2:mem:testdb"
//    ]
//)
//@AutoConfigureMockMvc
//class ItemControllerTest(@Autowired val client: TestRestTemplate) {
//    @Autowired
//    lateinit var itemRepository: ItemRepository
//
//    companion object {
//        var END_POINT_HELLO = "/"
//        var END_POINT_CREATE = "/item/create"
//        var END_POINT_LIST = "/item/list"
//    }
//
//    fun beforeAll(){
//    }

//    @Test
//    fun `test init endpoint`() {
//        val entity : Any = client.getForObject<String>(END_POINT_HELLO)
//        println("entity start ================================")
//        println(entity)
//        assertThat(entity.statusCode).isEqualTo(HttpStatus.OK)
//
//    }


//        // given
//        val body = mapOf(
//            "productName" to "갤럭시 노트10 5G",
//            "title" to "갤럭시 노트 10 5G 사용 1년반 팝니다.",
//            "content" to "이거 팔릴까요?",
//            "price" to 350000,
//        )



//}
//
//@ExtendWith(MockitoExtension.class)
//class MockTests{
//    @Mock
//    lateinit  val hellocontroller: HelloController
//
//}


//@WebMvcTest(HelloController::class)
//class ReadTodoTests(@Autowired val mockMvc: MockMvc) {
//
//    @Test
//    fun `Assert get right contents`() {
//        mockMvc.get("/"){
//        }.andExpect {
//            status { status().isOk }
//            content { contentType("text/html;charset=UTF-8") }
//        }
//    }
//
//    @Test
//    fun `Assert get right Item`() {
//        val output = mockMvc.post("/item/create"){
//            content = mapOf(
//                "productName" to "갤럭시 노트10 5G",
//                "title" to "selling 갤럭시 노트10 5G",
//                "cotent" to "2년정도 사용",
//                "price" to 350000,
//            )
//        }.andExpect {
//            status { status().isOk }
//            content { json("{item:{productName: '갤럭시 노트10 5G'}}") }
//        }
//    }
//}


/* when */
//        val actions = mockMvc.perform(get("/"))
//        println("log =======================================")
//        println(actions.toString())
//        println(actions)

/* then */
//        actions.andExpect(status().isOk)
//            .andExpect(content().string("gwangho"))
//            .andExpect(content().string("HERE"))



//.andExpect {
//    status { isOk }
//    content { contentType(MediaType.APPLICATION_JSON) }
//    content { json("{}") }
//}


//HTTP/1.1 200
//Content-Type: text/html;charset=UTF-8
//Content-Language: ko-KR
//Content-Length: 1427
//Date: Sat, 24 Jul 2021 23:46:51 GMT
//Keep-Alive: timeout=60
//Connection: keep-alive
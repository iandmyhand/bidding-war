package com.peoplefund.biddingwar.product

import AcceptanceTest
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import io.restassured.RestAssured
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType


class ProductAcceptanceTest : AcceptanceTest() {

    private val objectMapper = ObjectMapper().registerModule(KotlinModule())

    @BeforeEach
    override fun setUp() {
        super.setUp()
        `상품 등록 요청`(ProductCreateRequest("아파트 담보(의정부시 의정부동) 1111", 1_000_000L))
        `상품 등록 요청`(ProductCreateRequest("아파트 담보(서울시 강남구) 2233", 5_000_000L))
        `상품 등록 요청`(ProductCreateRequest("개인 채권 3234", 7000000L))
    }

    @Test
    internal fun `상품 등록`() {
        val createResponse = `상품 등록 요청`(
            ProductCreateRequest("주담대 1", 50_000_000, "주당대 상품입니다.")
        )

        assertThat(createResponse?.statusCode()).isEqualTo(HttpStatus.CREATED.value())
        assertThat(createResponse?.header(HttpHeaders.LOCATION)).containsSubsequence("/api/products/")
    }

    @Test
    internal fun `상품 단건 조회`() {
        val productId = 1L
        val response = `상품 단건 조회 요청`(productId)

        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        val productResponse = response.`as`(ProductResponse::class.java)
        assertThat(productResponse.id).isEqualTo(productId)
        assertThat(productResponse.name).isEqualTo("아파트 담보(의정부시 의정부동) 1111")
        assertThat(productResponse.amount).isEqualTo(1000000L)
        assertThat(productResponse.description).isEqualTo(null)
    }

    @Test
    internal fun `전체 상품 목록 조회`() {
        val response = `상품 목록 조회 요청`(mapOf())

        val products = response.`as`(List::class.java)
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(products.count()).isEqualTo(3)
    }

    @Test
    internal fun `상품 목록 조회(필터링 이름)`() {
        val response = `상품 목록 조회 요청`(mapOf("name" to "담보"))

        val products = response.`as`(List::class.java)
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value())
        assertThat(products.count()).isEqualTo(2)
    }

    private fun `상품 등록 요청`(productCreateRequest: ProductCreateRequest) = RestAssured
        .given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .body(objectMapper.writeValueAsString(productCreateRequest))
        .`when`().post("/api/products")
        .then().log().all().extract()

    private fun `상품 목록 조회 요청`(params: Map<String, String>) = RestAssured
        .given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .params(params)
        .`when`().get("/api/products")
        .then().log().all().extract()

    private fun `상품 단건 조회 요청`(productId: Long) = RestAssured
        .given().log().all()
        .contentType(MediaType.APPLICATION_JSON_VALUE)
        .`when`().get("/api/products/{productId}", productId)
        .then().log().all().extract()

}

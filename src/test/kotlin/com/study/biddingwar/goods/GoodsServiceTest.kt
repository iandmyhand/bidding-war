package com.study.biddingwar.goods

import jdk.jfr.Description
import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class GoodsServiceTest {

    @BeforeAll
    fun initGoodsList() {
        /**
         * Test시나리오 마다 돌릴지 특정 시나리오에만 돌릴지 고민해보자!
         * --
         * 1. 실제 테이블을 기준으로 셋업할 것인지? --> 구분값 추가
         * 2. 별도의 테스트 테이블을 생성할 것인지?
         * --
         * MockService @TODO: 작업 진행
         */
        initGoodsTable()
        setGoodsList()
    }

    @Test
    @Description("상품 전체 목록 가져오기")
    fun getGoodsList() {

    }

    @Test
    @Description("상품 상세 가져오기")
    fun getGoodsInfo() {

    }

    private fun initGoodsTable() {
        println("-------- 상품 테이블 초기화 ---------")
    }

    private fun setGoodsList() {
        println("-------- 상품 테이블 리스트 초기 셋업 ---------")
    }
}
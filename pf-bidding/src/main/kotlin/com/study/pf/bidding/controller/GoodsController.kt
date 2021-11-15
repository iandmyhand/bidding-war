package com.study.pf.bidding.controller

import com.study.pf.bidding.service.GoodsService
import com.study.pf.bidding.domain.dto.GoodsInfoDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.Parameters
import io.swagger.v3.oas.annotations.enums.ParameterIn
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/goods")
class GoodsController(private val goodsService: GoodsService) {

    /**
     * 1~2주차 구현해야할 목록
     * =============================================
     * 1. 상품 등록 API
     * 2. 목록 조회 API
     * 3. 단건 조회 API
     * + Front 페이지 구현
     * ---
     *
     * - 상품 목록 페이징 처리 +?
     * - 상품 디테일 / 검색조건? 고려??
     * - 밸리데이션은 추후 고도화
     */

    @Operation(summary = "goods list paging show",
        description = "상품 리스트 페이징 조회",
        security = [SecurityRequirement(name = "bearer-key")])
    @Parameters(
        value = [
            Parameter(
                name = "rows",
                description = "show lines - int", `in` = ParameterIn.HEADER
            ),
            Parameter(
                name = "nowPage",
                description = "now showing page - int", `in` = ParameterIn.HEADER
            ),
            Parameter(
                name = "searchName",
                description = "goods Id OR Name .. - String", `in` = ParameterIn.HEADER
            ),
            Parameter(
                name = "searchType",
                description = "goods Type(category) - String", `in` = ParameterIn.HEADER
            )
        ]
    )
    @GetMapping("/list")
    fun getGoodsList(@RequestParam(name = "rows") rows: Int
                    , @RequestParam(name = "nowPage") nowPage: Int
                    , @RequestParam(name = "searchName") searchName: String
                    , @RequestParam(name = "searchType") searchType: String
    ): ResponseEntity<List<GoodsInfoDto>> {
        val goodsList = this.goodsService.getGoodsList(PageRequest.of(nowPage - 1, rows), searchName, searchType)
        println("goodsList: " +  goodsList.toList())
        // vue 에서 Page 객체 받는 법 확인할 것...Page 객체로 repo에서 받은뒤 서비스에서 분해?
        return ResponseEntity.ok().body(goodsList.toList())
    }

    @GetMapping("/detail/{id}")
    fun getGoodsInfo(@PathVariable(name = "id") goods_id: Long
    ): ResponseEntity<GoodsInfoDto> {
        val goodsInfo = this.goodsService.getGoodsInfo(goods_id)
        return ResponseEntity.ok().body(goodsInfo)
    }

    @PostMapping("/regist")
    fun registGoods(@RequestBody goodsInfoDto: GoodsInfoDto
    ): ResponseEntity<GoodsInfoDto> {
        val addedGoodsInfo = this.goodsService.registGoods(goodsInfoDto)
        return ResponseEntity.ok().body(addedGoodsInfo)
    }

    @PutMapping("/modify")
    fun modifyGoods(@RequestBody goodsInfoDto: GoodsInfoDto
    ): ResponseEntity<GoodsInfoDto> {
        val modifiedGoodsInfo = this.goodsService.modifyGoods(goodsInfoDto)
        return ResponseEntity.ok().body(modifiedGoodsInfo)
    }
}
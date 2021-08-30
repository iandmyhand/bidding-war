package com.study.biddingwar.controller

import com.study.biddingwar.domain.dto.GoodsInfoDto
import com.study.biddingwar.domain.dto.GoodsSearchDto
import com.study.biddingwar.service.GoodsService
import org.springframework.data.domain.Page
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

    @GetMapping("/list")
    fun getGoodsList(@RequestParam(name = "rows") rows: Int
                    , @RequestParam(name = "nowPage") nowPage: Int
                    , @RequestParam(name = "searchName", required = false) searchName: String
                    , @RequestParam(name = "searchType", required = false) searchType: String
    ): ResponseEntity<Page<GoodsInfoDto>> {
    //  println("rows : $rows, nowPage : $nowPage, name : $searchName, type : $searchType")

        /**
         * 비효율 적인 것 같음 --> 삭제
        val goodsSearchDto = GoodsSearchDto(
            nowPage = nowPage,
            rows = rows,
            searchName = searchName,
            searchType = searchType
        )
        **/

        val goodsList = this.goodsService.getGoodsList(PageRequest.of(nowPage - 1, rows), searchName, searchType)
        return ResponseEntity.ok().body(goodsList)
    }

    @GetMapping("/defatil/{id}")
    fun getGoodsInfo(@PathVariable(name = "goods_id") goods_id: Long
    ): ResponseEntity<GoodsInfoDto> {
        val goodsInfo = this.goodsService.getGoodsInfo(goods_id)
        return ResponseEntity.ok().body(goodsInfo)
    }

    @PostMapping("/add")
    fun addGoods(@RequestBody goodsInfoDto: GoodsInfoDto
    ): ResponseEntity<GoodsInfoDto> {
        val addedGoodsInfo = this.goodsService.addGoods(goodsInfoDto)
        return ResponseEntity.ok().body(addedGoodsInfo)
    }

    // post? put? --> put
    @PutMapping("/modify")
    fun modifyGoods(@RequestBody goodsInfoDto: GoodsInfoDto
    ): ResponseEntity<GoodsInfoDto> {
        val modifiedGoodsInfo = this.goodsService.modifyGoods(goodsInfoDto)
        return ResponseEntity.ok().body(modifiedGoodsInfo)
    }
}
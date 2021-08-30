package com.study.biddingwar.service

import com.study.biddingwar.domain.dto.GoodsInfoDto
import com.study.biddingwar.domain.dto.GoodsSearchDto
import com.study.biddingwar.domain.entity.Goods
import com.study.biddingwar.repository.GoodsRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class GoodsService(private val goodsRepository: GoodsRepository) {
    
    // 상품 전체 리스트 조회
    fun getGoodsList(pageRequest: PageRequest
                     , searchName: String
                     , searchType: String): Page<GoodsInfoDto> {
        /**
         * searchName & searchType의 콜라보로 if 길어지는것을 방지하고자 native 쿼리 사용함
         * 케이스가 최소 3개이상 생김..
         * --> 비어있을 경우 빈 리스트 반환하는게 맞아보임 throw 는 일단 두고 바꾸어야함
         */
        var goodsList = this.goodsRepository
                .findAllGoodsWithPaginationByNameAndCategory(
                        pageRequest,
                        searchName,
                        searchType
                )?: throw NoSuchElementException("[goodsList] no such element goods list ... update requirement !!")

        return goodsList;
    }
    
    // 상품 상세 조회
    fun getGoodsInfo(id: Long): GoodsInfoDto {
        val goodsInfo = this.goodsRepository
                .findById(id) // optional 반환으로 인한 아래 로직 추가
                .orElseThrow { NoSuchElementException("[getGoodsInfo] no such element goods : $id") } // 개선하고 싶음

        return GoodsInfoDto(
                goodsInfo.id!!,
                goodsInfo.goodsName,
                goodsInfo.goodsPrice,
                goodsInfo.goodsContent,
                goodsInfo.goodsCategory
        )
    }

    // 상품 등록
    @Transactional
    fun addGoods(goodsInfo: GoodsInfoDto
    ): GoodsInfoDto {
        // @TODO:값 검증
        val addGoodsInfo = Goods(
            goodsName = goodsInfo.goodsName,
            goodsPrice = goodsInfo.goodsPrice,
            goodsContent = goodsInfo.goodsContent,
            goodsCategory = goodsInfo.goodsCatetory
        )
        return this.goodsRepository.save(addGoodsInfo).let {
            GoodsInfoDto(it.id!!, it.goodsName, it.goodsPrice, it.goodsContent, it.goodsCategory)
        }
    }

    // 상품 수정
    @Transactional
    fun modifyGoods(goodsInfo: GoodsInfoDto
    ): GoodsInfoDto {
        val modifyInfo = this.goodsRepository.findById(goodsInfo.goodsId)
                .orElseThrow { NoSuchElementException("[modifyGoods] no such element goods : ${goodsInfo.goodsId}") }

        modifyInfo.setGoodsCategory(goodsInfo.goodsCatetory)
        modifyInfo.setGoodsName(goodsInfo.goodsName)
        modifyInfo.setGoodsContent(goodsInfo.goodsContent)
        modifyInfo.setGoodsPrice(goodsInfo.goodsPrice)

        return this.goodsRepository.save(modifyInfo).let {
            GoodsInfoDto(it.id!!, it.goodsName, it.goodsPrice, it.goodsContent, it.goodsCategory)
        }
    }

    // 상품 삭제 --> @TODO
    fun deleteGoods(goodsInfo: GoodsInfoDto
    ): String {
        return ""
    }
}
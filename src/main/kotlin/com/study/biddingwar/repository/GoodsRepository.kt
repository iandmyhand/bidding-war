package com.study.biddingwar.repository

import com.study.biddingwar.domain.dto.GoodsInfoDto
import com.study.biddingwar.domain.entity.Goods
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface GoodsRepository: JpaRepository<Goods, Long> {

    @Query(
            value = "SELECT g.* FROM Goods g WHERE g.goods_name like %:searchName%"
                    + " and g.goods_category = :searchType"
            , countQuery = "SELECT count(g.*) FROM Goods g WHERE g.goods_name like %:searchName%"
                    + " and g.goods_category = :searchType"
            , nativeQuery = true)
    fun findAllGoodsWithPaginationByNameAndCategory(pageable: Pageable, searchName: String, searchType: String): Page<GoodsInfoDto>

    fun findByGoodsNameContainingAndGoodsCategoryEquals(pageable: Pageable, goodsName: String, goodsCategory: String): Page<Goods>
}
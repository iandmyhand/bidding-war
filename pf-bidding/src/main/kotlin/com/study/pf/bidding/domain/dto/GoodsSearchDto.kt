package com.study.pf.bidding.domain.dto

/**
 * @TODO: 파라미터 3~4개이하? 그대로 활용 --> 해당 Dto 검토후 안 사용하면 삭제~~
 */
data class GoodsSearchDto(
    var nowPage: Int,
    var rows: Int,
    var searchName: String?=null,
    var searchType: String?=null
)
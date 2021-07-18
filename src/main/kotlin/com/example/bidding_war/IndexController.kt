package com.example.bidding_war

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class IndexController {
    @GetMapping(path = ["/hello"])
    fun index() = "Hello Spring!"


}


// 상품 등록 API
// 목록 조회 API
// 단건 조회 API

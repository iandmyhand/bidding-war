package com.example.bidding_war

class Product(val product_id : Long,
              val product_name : String,
              val description : String
                ) {
    override fun toString(): String {
        return "product_id=$product_id, product_name=$product_name, description=$description"
    }
}


// 상품 등록 API
// 목록 조회 API
// 단건 조회 API

package com.study.pf.common.common.exception

interface BiddingError {
    val code: String
    val message: String

    // 공통 - C
    enum class Common (
            override val code: String,
            override val message: String
    ): BiddingError {
        UNEXPECTED_ERROR(
            "C.001",
            "Internal server error !!"
        ),
    }

    // 상품 관련 -  G
    enum class Goods (
            override val code: String,
            override val message: String
    ) : BiddingError {
        GOODS_NOT_FOUND(
            "G.001",
            "Cannot found goods"
        ),

    }

    // User 관련 - U
    enum class User (
            override val code: String,
            override val message: String
    ) : BiddingError {
        USER_NOT_FOUND(
            "U.001",
            "Cannot found user info"
        ),

        DUPLICATE_SIGNUP_USER(
            "U.002",
            "Already joined user info"
        ),

        IS_BLOCK_USER(
            "U.002",
            "Cannot use user info"
        )
    }

    // Session 관련 - S
    enum class Seesion (
            override val code: String,
            override val message: String
    ) : BiddingError {
        SESSION_NOT_FOUND(
            "S.001",
            "Cannot found Session Info"
        )
    }
}
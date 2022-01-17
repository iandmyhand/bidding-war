package com.study.biddingwar.exception

enum class ErrorType(val code: String, val message: String) {

    CSRF_TOKEN("INVALID_CSRF_TOKEN","invalid csrf token"),
    NOT_FOUND_RSAKEY("NOT_FOUND_RSAKEY", "not found rsa-key cache"),
    DATA_DUPLICATION("DATA_DUPLICATION", "data duplication conflict")
}
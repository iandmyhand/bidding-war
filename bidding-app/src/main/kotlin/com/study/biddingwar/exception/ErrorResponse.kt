package com.study.biddingwar.exception

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema

data class ErrorResponse<DATA>(
    @Schema(description = "pf-user 서비스 실패 코드", required = true)
    @get:JsonProperty
    var code: String,
    @Schema(description = "pf-user 서비스 실패 메시지", required = true)
    @get:JsonProperty
    var message: String,
    @Schema(description = "pf-user 서비스 실패 추가 data object")
    @get:JsonProperty
    var data: DATA? = null
){
    companion object {
        fun <DATA> wrap(error: ErrorType): ErrorResponse<DATA> {
            return ErrorResponse(error.code, error.message)
        }

        fun <DATA> wrap(error: ErrorType, detailMessage: String): ErrorResponse<DATA> {
            return ErrorResponse(
                error.code, "${error.message}, $detailMessage"
            )
        }

        fun <DATA> wrap(
            error: ErrorType,
            detailMessage: String,
            data: DATA
        ): ErrorResponse<DATA> {
            return ErrorResponse(
                error.code, "${error.message}, $detailMessage", data
            )
        }
    }
}

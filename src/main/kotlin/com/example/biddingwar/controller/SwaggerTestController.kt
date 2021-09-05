package com.example.biddingwar.controller

import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiParam
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.ApiResponses
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

class SwaggerTestController {
    @ApiOperation(value = "HTTP GET EXAMPLE", notes = "GET 요청에 대한 예제 입니다.")
    @ApiResponses(
        ApiResponse(code = 200, message = "성공"),
        ApiResponse(code = 500, message = "서버에러"),
        ApiResponse(code = 404, message = "찾을 수 없음")
    )
    @RequestMapping(value = ["/test"], method = [RequestMethod.GET, RequestMethod.POST])
    @ResponseBody
    fun main(
        @ApiParam(value = "테스트 파라미터_1", required = true, example = "test_parameter_1") @RequestParam test1: String,
        @ApiParam(value = "테스트 파라미터_2", required = true, example = "test_parameter_2") @RequestParam test2: String
    ): String? {
        return "$test1 : $test2"
    }
}
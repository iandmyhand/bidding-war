package com.example.biddingwar.controller

import com.example.biddingwar.database.dto.UserSignUpRequestDto
import com.example.biddingwar.database.dto.UserSignUpResponseDto
import com.example.biddingwar.database.entity.User
import com.example.biddingwar.service.user.UserService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@Api(description = "유저")
@RestController
@RequestMapping("")
class UserController(val service: UserService) {
    @GetMapping("/users")
    @ApiOperation(value = "유저 조회", notes = "유저 조회 GET API")
    fun users() = ResponseEntity.ok(service.getAll())

    @PostMapping("/sign-up")
    @ApiOperation(value = "회원가입", notes = "회원가입 POST API")
    fun signUp(@RequestBody userSignUpRequestDto: UserSignUpRequestDto) : ResponseEntity<UserSignUpResponseDto> {
        return ResponseEntity.ok().body(service.signUp(userSignUpRequestDto))
    }

    @PostMapping("/sign-in")
    @ApiOperation(value = "로그인", notes = "로그인 POST API")
    fun signIn(@RequestBody user: User, request: HttpServletRequest): ResponseEntity<String> {
        return service.signIn(user, request)
    }
}
package com.peoplefund.biddingwar.users

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.*
import java.net.URI
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpSession
import javax.validation.Valid

@RestController
@RequestMapping("/api/users")
class UserController(@Autowired val userService: UserService)  {

    @PostMapping("/signup")
    fun signUp(@RequestBody @Valid userRequestBody: UserSignupRequest, result: BindingResult): ResponseEntity<Any> {
        val signedUpUser = userService.signUp(userRequestBody)

        return ResponseEntity
            .created(URI.create("/api/users/" + signedUpUser.userId))
            .body(mapOf("success" to true))
    }

    @PostMapping("/signin")
    fun signIn(
        @RequestBody @Valid
        userRequestBody: UserSignupRequest,
        result: BindingResult,
        session: HttpSession,
    ): ResponseEntity<UserSigninResponse> {

        val signInUser = userService.signIn(userRequestBody)
        val userSigninResponse = UserSigninResponse(signInUser)

        session.setAttribute("loginMember", userSigninResponse.userId)

        return ResponseEntity.ok(userSigninResponse)
    }

    @GetMapping("/logout")
    fun logout(request: HttpServletRequest): ResponseEntity<Map<String, Any>> {
        request.getSession(false)?.invalidate()
        return ResponseEntity.ok(mapOf("success" to true))
    }

    @ExceptionHandler(AlreadyExistUserException::class)
    fun handleAlreadyExistUserException(e: AlreadyExistUserException?): ResponseEntity<Any> {
        return ResponseEntity
            .badRequest()
            .build()
    }

    @ExceptionHandler(NoMatchedSignInException::class)
    fun handleNoMatchedSignInException(e: NoMatchedSignInException?): ResponseEntity<Any> {
        return ResponseEntity
            .badRequest()
            .build()
    }
}
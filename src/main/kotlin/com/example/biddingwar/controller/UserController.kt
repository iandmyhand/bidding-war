package com.example.biddingwar.controller

import com.example.biddingwar.dto.UserLoginForm
import com.example.biddingwar.dto.UserSignInForm
import com.example.biddingwar.repository.UserRepository
import io.swagger.annotations.ApiOperation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.servlet.http.HttpSession

@Controller
@RequestMapping("/user")
class UserController(@Autowired val userRepository: UserRepository) {

    @ApiOperation(value = "User sign in form", notes = "사용자 회원 가입 폼")
    @GetMapping("/signin")
    fun signin_get(): String {
        return "Users/signInForm"
    }

    @ApiOperation(value = "process User sign in", notes = "사용자 회원 가입 처리")
    @PostMapping("/signin")
    fun signin_post(userForm : UserSignInForm): String{

        if(userRepository.findUserByUserId(userForm.userId) != null || userForm.userPw.length < 4){
            return "redirect:/user/signin"
        }
        userRepository.save(userForm.toEntity())

        return "redirect:/user/login"
    }

    @ApiOperation(value = "User login form", notes = "사용자 로그인 폼")
    @GetMapping("/login")
    fun login_get(session: HttpSession, model: Model): String {
        val userId = session.getAttribute("userId")

        return "Users/loginForm"
    }

    @ApiOperation(value = "process User login", notes = "사용자 로그인 처리")
    @PostMapping("/login")
    fun login_post(userForm : UserLoginForm, session: HttpSession, model:Model): String{
        val (userId, userPw) = userForm.toEntity()
        val user = userRepository.findUserByUserIdAndUserPw(userId, userPw)
        var response: String = ""

        if (user != null){
            session.setAttribute("userId", user.userId)

            model["userName"] = user.userName
            model["content"] = "로그인을 환영합니다."

            response = "greetings"
        }else{
            response = "redirect:/user/login?error=1"
        }

        return response
    }

    @ApiOperation(value = "process User logout", notes = "사용자 로그인아웃 처리")
    @GetMapping("/logout")
    fun logout_post(session: HttpSession, model:Model): String{
        val userId = session.getAttribute("userId")

        if(userId != null){
            session.removeAttribute("userId")
            session.invalidate()

            print("userID is not None ${userId}")
        }

        return "redirect:/welcome/bye"
    }
}
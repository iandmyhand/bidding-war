package com.study.biddingwar.user

import com.study.biddingwar.service.UserService
import jdk.jfr.Description
import org.aspectj.lang.annotation.Before
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserServiceTest {
    companion object {
        
    }

    @BeforeAll
    fun initUserInfo() {

    }
    
    @Test
    @Description("로그인 성공 사례")
    fun signInUserSuccess() {
        
    }

    @Test
    @Description("로그인 실패 - 없는 아이디로 로그인 시도")
    fun signInUserFailer1() {

    }
    
    @Test
    @Description("로그인 실패 - 패스워드 불일치")
    fun signInUserFailer2() {

    }

    @Test
    @Description("회원가입 성공")
    fun signUpUserSuccess() {

    }

    @Test
    @Description("회원가입 실패 - 중복가입")
    // 이 외의 케이스는 별도의 방어 프로세스 구현 필요
    fun signUpUserFailer1() {

    }

}
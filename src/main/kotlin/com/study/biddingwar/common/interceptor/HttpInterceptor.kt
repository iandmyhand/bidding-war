package com.study.biddingwar.common.interceptor

import org.springframework.context.annotation.Configuration
import org.springframework.lang.Nullable
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import java.net.http.HttpResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Configuration
class HttpInterceptor : HandlerInterceptor {

    override fun preHandle(httpRequest: HttpServletRequest,
                           httpServletResponse: HttpServletResponse,
                           handle: Any): Boolean {
        // 특정 로직 추가할 것! --> 현재는 전부 true
        println("preHandle ...")
        return true;
    }

    override fun postHandle(httpRequest: HttpServletRequest,
                   httpServletResponse: HttpServletResponse,
                   handle: Any,
                   modelAndView: ModelAndView?) {
        println("postHandle ...")
    }

}
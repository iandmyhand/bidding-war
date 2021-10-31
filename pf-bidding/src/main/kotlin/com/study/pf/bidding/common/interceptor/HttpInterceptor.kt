package com.study.pf.bidding.common.interceptor

import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import org.springframework.web.servlet.ModelAndView
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
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
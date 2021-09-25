package com.study.peoplefund.web.arguments.user

import com.study.peoplefund.service.AuthService
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

@Component
class UserArgumentResolver(
        val authService: AuthService
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@")
        println(parameter.getParameterAnnotation(AuthInfo::class.java) != null
                && parameter.parameterType == Long::class.java)
        return parameter.getParameterAnnotation(AuthInfo::class.java) != null
                && parameter.parameterType == Long::class.java
    }

    override fun resolveArgument(
            parameter: MethodParameter,
            mavContainer: ModelAndViewContainer?,
            webRequest: NativeWebRequest,
            binderFactory: WebDataBinderFactory?
    ): Long {
        val request = webRequest.nativeRequest as HttpServletRequest

        val token = request.getHeader("Authorization")

        return authService.validateToken(token)
    }
}
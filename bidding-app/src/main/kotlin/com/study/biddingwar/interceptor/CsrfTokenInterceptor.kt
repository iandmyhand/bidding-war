package com.study.biddingwar.interceptor

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.biddingwar.exception.ErrorResponse
import com.study.biddingwar.exception.ErrorType
import com.study.biddingwar.service.SecuritySupportService
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class CsrfTokenInterceptor(private val securitySupportService:SecuritySupportService,
                           private val objectMapper: ObjectMapper ): HandlerInterceptor{

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {

        if(!request.getHeader("X-CSRF-TOKEN").isNullOrBlank()){
            val csrfToken:String = request.getHeader("X-CSRF-TOKEN")
            val isExist = securitySupportService.existCsrfToken(csrfToken)

            if(!isExist) {
                response.status = HttpStatus.FORBIDDEN.value()
                val jsonTxt = objectMapper.writeValueAsString(ErrorResponse.wrap<Any>(ErrorType.CSRF_TOKEN))
                response.writer.write(jsonTxt)
                return false
            }
            securitySupportService.deleteCsrfToken(csrfToken)
        }
        return super.preHandle(request, response, handler)
    }


}
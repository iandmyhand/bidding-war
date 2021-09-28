package com.study.biddingwar.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.biddingwar.common.crypto.CryptoRsaUtils
import com.study.biddingwar.domain.RsaKeyStore
import com.study.biddingwar.exception.ErrorResponse
import com.study.biddingwar.exception.ErrorType
import org.springframework.http.HttpStatus
import org.springframework.web.filter.OncePerRequestFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

//@Component
//@WebFilter(
//    description = "RSA encrypted-body decryption filter",
//    filterName = "decryption-filter",
//    urlPatterns = arrayOf("/v1/product"))
class DecryptFilter(private val objectMapper: ObjectMapper): OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        var request = request
        if(!request.getHeader("RSA-ENCRYPT-KEY-ID").isNullOrBlank()){
            val keyId = request.getHeader("RSA-ENCRYPT-KEY-ID")
            var secretKey = request.getHeader("AES-ENCRYPT-KEY")
            val privateKeyMaps = RsaKeyStore.getInstance().getRsaPrivateKeys()
            //TODO: privateKey없으면 exception처리 해야함.
            val privateKey = privateKeyMaps?.get(keyId)
            if(privateKey != null){
                secretKey = CryptoRsaUtils.decryptMsg(secretKey, privateKey!!)
//            request = RequestRSADecryptWrapper(request = request, privateKey!!)
                request = RequestAESDecryptWrapper(request = request, secretKey!!)
            }else{
                val jsonTxt = objectMapper.writeValueAsString(ErrorResponse.wrap<Any>(ErrorType.NOT_FOUND_RSAKEY))
                response.writer.write(jsonTxt)
                response.status = HttpStatus.INTERNAL_SERVER_ERROR.value()
                return
            }
        }
        filterChain.doFilter(request, response)
    }

}
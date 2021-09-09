package com.study.biddingwar.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.biddingwar.domain.RsaKeyCache
import com.study.biddingwar.domain.RsaKeyStore
import com.study.biddingwar.repository.RedisRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.security.PrivateKey
import javax.annotation.PostConstruct

@Service
class SecuritySupportService(private val securityRedisRepository: RedisRepository,
                             private val objectMapper: ObjectMapper) {

    @PostConstruct
    fun initRsaKetCache(){
        try {
            refreshRsaKeyCache()
        }catch(e: Exception){
            logger.error(e.message)
        }
    }

    fun refreshRsaKeyCache(){
        val rsaKeyCacheJson:String = securityRedisRepository.get("rsa:key:cache") as String
        val rsaKeyCache = objectMapper.readValue(rsaKeyCacheJson,RsaKeyCache::class.java)
        RsaKeyStore.getInstance().setRsaKeyCache(rsaKeyCache)
    }

    fun getRsaKeyCache(): RsaKeyCache? {
        return RsaKeyStore.getInstance().getRsaKeyCache()
    }

    fun getRsaPrivateKeys(): Map<String,PrivateKey?>? {
        val currnetKey: PrivateKey? = RsaKeyStore.getInstance().getRsaKeyCache()?.currentRsaKeyPair?.privateKey
        val nextKey: PrivateKey? = RsaKeyStore.getInstance().getRsaKeyCache()?.nextRsaKeyPair?.privateKey

        if(currnetKey != null && nextKey != null){
            return mapOf(
                RsaKeyStore.getInstance().getRsaKeyCache()?.currentRsaKeyPair?.keyId.toString() to currnetKey,
                RsaKeyStore.getInstance().getRsaKeyCache()?.nextRsaKeyPair?.keyId.toString() to nextKey
            )
        }else if(currnetKey != null){
            return mapOf(
                RsaKeyStore.getInstance().getRsaKeyCache()?.currentRsaKeyPair?.keyId.toString() to currnetKey
            )
        }
        return null
    }

    fun getCsrfToken(csrfToken:String): String {
        return securityRedisRepository.get("csrf:$csrfToken") as String
    }

    fun getCsrfToken(userId:Long, csrfToken:String): String {
        return securityRedisRepository.get("csrf:$userId:$csrfToken") as String
    }

    fun existCsrfToken(csrfToken:String): Boolean {
        val isResult = securityRedisRepository.getLock("csrf:$csrfToken", csrfToken)
        return isResult
    }

    fun existCsrfToken(userId:Long, csrfToken:String): Boolean {
        val isResult = securityRedisRepository.getLock("csrf:$userId:$csrfToken", csrfToken)
        return isResult
    }

    fun deleteCsrfToken(csrfToken: String){
        securityRedisRepository.delete("csrf:$csrfToken")
    }

    fun deleteCsrfToken(userId:Long, csrfToken: String){
        securityRedisRepository.delete("csrf:$userId:$csrfToken")
    }

    companion object{
        private val logger = LoggerFactory.getLogger(this::class.java)
    }

}
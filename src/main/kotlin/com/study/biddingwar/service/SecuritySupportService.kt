package com.study.biddingwar.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.biddingwar.domain.RsaKeyCache
import com.study.biddingwar.domain.RsaKeyStore
import com.study.biddingwar.repository.RedisRepository
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct

@Service
class SecuritySupportService(private val securityRedisRepository: RedisRepository,
                             private val objectMapper: ObjectMapper) {

    @PostConstruct
    fun initRsaKetCache(){
        refreshRsaKeyCache()
    }

    fun refreshRsaKeyCache(){
        val rsaKeyCacheJson:String = securityRedisRepository.get("rsa:key:cache") as String
        val rsaKeyCache = objectMapper.readValue(rsaKeyCacheJson,RsaKeyCache::class.java)
        RsaKeyStore.getInstance().setRsaKeyCache(rsaKeyCache)
    }

    fun getRsaKeyCache(): RsaKeyCache? {
        return RsaKeyStore.getInstance().getRsaKeyCache()
    }

}
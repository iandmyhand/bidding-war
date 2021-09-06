package com.study.biddingwar.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.study.biddingwar.domain.RsaKeyCache
import com.study.biddingwar.domain.RsaKeyStore
import com.study.biddingwar.repository.RedisRepository
import org.springframework.stereotype.Service
import java.security.PrivateKey
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

}
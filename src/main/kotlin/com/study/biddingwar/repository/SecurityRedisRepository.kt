package com.study.biddingwar.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class SecurityRedisRepository(private val securityRedisTemplate: RedisTemplate<String, Any>):RedisRepository {
    override fun get(key:String): Any? {
        return securityRedisTemplate.opsForValue().get(key)
    }

    //Lock을 획득 못하는건 존재하지 않거나 이미 lock이 된 상태이다.
    override fun getLock(key: String, value:String): Boolean {
        val isResult:Boolean? = securityRedisTemplate.opsForValue().setIfPresent(key,value)?:(false)
        if(isResult!=true){
            return false
        }
        return true
    }

    override fun <T>put(key:String, DATA:T){
        securityRedisTemplate.opsForValue().set(key, DATA!!)
    }

    override fun delete(key:String){
        securityRedisTemplate.delete(key)
    }

    override fun expire(key:String){
        securityRedisTemplate.expire(key, Duration.ofHours(1))
    }
}
package com.study.biddingwar.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class SecurityRedisRepository(private val securityRedisTemplate: RedisTemplate<String, Any>):RedisRepository {
    override fun get(key:String): Any? {
        return securityRedisTemplate.opsForValue().get(key)
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
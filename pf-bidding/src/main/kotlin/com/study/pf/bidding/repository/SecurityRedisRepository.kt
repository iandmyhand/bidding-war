package com.study.pf.bidding.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class SecurityRedisRepository(private val securityRedisTemplate: RedisTemplate<String, Any> ) {

    fun get(key: String): Any? {
        return securityRedisTemplate.opsForValue().get(key)
    }

    fun<T> put(key: String, DATA: T) {
        securityRedisTemplate.opsForValue().set(key, DATA!!)
    }

    fun delete(key: String) {
        securityRedisTemplate.delete(key)
    }

    fun expire(key: String) {
        securityRedisTemplate.expire(key, Duration.ofMinutes(10))
    }
}
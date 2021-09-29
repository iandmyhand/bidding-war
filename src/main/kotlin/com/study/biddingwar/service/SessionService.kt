package com.study.biddingwar.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.study.biddingwar.domain.dto.SessionDto
import com.study.biddingwar.repository.SecurityRedisRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SessionService(private val securityRedisRepository: SecurityRedisRepository,
                     private val objectMapper: ObjectMapper
) {
    companion object {
        private val logger = LoggerFactory.getLogger(this::class.java)
        private val expire: Int = 1000
    //  private val mapper = jacksonObjectMapper()
    }

    fun setSessionDto(sessionDto: SessionDto) {

        if (sessionDto.userId.isNullOrBlank()) {
            throw Exception("required SessionDto.userId ..")
        }

        val loginSessionDto = getSessionDto(sessionDto.userId)
        loginSessionDto?.let {
            throw Exception("already login user..!!")
        }

        /**
         * - 일단 사용자 ID로 키값 저장
         * - expire 현재 없음
         */
        try {
            // val jsonString = mapper.writeValueAsString(sessionDto)
            val jsonString = objectMapper.writeValueAsString(sessionDto)

            securityRedisRepository.put(sessionDto.userId, jsonString)
        } catch (exception: Exception) {
            throw Exception("sessionDto put error..!!")
        }
    }

    fun getSessionDto(key: String): SessionDto? {
        var redisValue = securityRedisRepository.get(key)

        redisValue?.let {
            val sessionDtoJson:String = securityRedisRepository.get(key) as String
            // val sessionDto = mapper.readValue(sessionDtoJson, SessionDto::class.java)
            val sessionDto = objectMapper.readValue(sessionDtoJson, SessionDto::class.java)

            return sessionDto
        } ?: return null

    }

    fun removeSession(key: String) {
        securityRedisRepository.delete(key)
    }

}

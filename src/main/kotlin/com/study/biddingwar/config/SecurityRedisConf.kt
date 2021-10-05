package com.study.biddingwar.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.RedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration

@Configuration
class SecurityRedisConf(@Value("\${spring.redis.host}") val host: String,
      @Value("\${spring.redis.port}") val port: Int,
      @Value("\${spring.redis.ssl}") val isSsl: Boolean
) { // @Study AbstractHttpSessionApplicationInitializer

    @Bean
    fun connectionFactory(): LettuceConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration(host, port)
        var lettuceClientConfigurationBuilder = LettuceClientConfiguration.builder()

        if(isSsl) {
            lettuceClientConfigurationBuilder.useSsl()
        }

        val lettuceClientConfiguration: LettuceClientConfiguration =
                lettuceClientConfigurationBuilder.commandTimeout(Duration.ofSeconds(3)).build()

        return LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration)
    }

    @Bean("securityRedisTemplate")
    fun securityRedisTemplate(): RedisTemplate<String, Any> {
        val redisTemplate = RedisTemplate<String, Any>()

        redisTemplate.setConnectionFactory(this.connectionFactory())
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = StringRedisSerializer()
        redisTemplate.hashKeySerializer = StringRedisSerializer()

        return redisTemplate
    }
}
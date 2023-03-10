package com.yellwsunn.authservice.config

import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import redis.embedded.RedisServer

@Profile("local", "local-gateway")
@Configuration
class EmbeddedRedisConfig(
    @Value("\${spring.data.redis.port}") private val redisPort: Int,
) {
    private lateinit var redisServer: RedisServer

    @PostConstruct
    fun startRedisServer() {
        redisServer = RedisServer.builder()
            .port(redisPort)
            .setting("maxmemory 512mb")
            .build()
        redisServer.start()
    }

    @PreDestroy
    fun stopRedisServer() {
        redisServer.stop()
    }
}

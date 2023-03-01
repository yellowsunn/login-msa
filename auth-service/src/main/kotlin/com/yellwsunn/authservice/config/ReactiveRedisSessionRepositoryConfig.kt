package com.yellwsunn.authservice.config

import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.session.data.redis.ReactiveRedisSessionRepository
import java.time.Duration

@Configuration
class ReactiveRedisSessionRepositoryConfig(
    private val sessionRepository: ReactiveRedisSessionRepository,
    @Value("\${redis-session.inactive-interval}") private val inactiveInterval: Duration,
) {

    @PostConstruct
    fun initMaxInactiveInterval() {
        // default: 30m
        sessionRepository.setDefaultMaxInactiveInterval(inactiveInterval)
    }
}

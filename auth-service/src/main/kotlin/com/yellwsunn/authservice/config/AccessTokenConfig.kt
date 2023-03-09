package com.yellwsunn.authservice.config

import com.yellowsunn.common.auth.accesstoken.AccessTokenHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class AccessTokenConfig {
    @Bean
    fun accessTokenHandler(
        @Value("\${access-token.secret}") secret: String,
        @Value("\${access-token.expiration}") expiration: Duration,
    ): AccessTokenHandler {
        return AccessTokenHandler(secret, expiration)
    }
}

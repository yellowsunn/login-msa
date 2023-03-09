package com.yellowsunn.apigatewayservice.config

import com.yellowsunn.common.auth.accesstoken.AccessTokenParser
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class AccessTokenConfig {
    @Bean
    fun accessTokenParser(
        @Value("\${access-token.secret}") secret: String,
    ): AccessTokenParser {
        return AccessTokenParser(secret)
    }
}

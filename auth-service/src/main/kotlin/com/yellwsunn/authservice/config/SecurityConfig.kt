package com.yellwsunn.authservice.config

import com.yellwsunn.authservice.security.oauth2.CustomRedirectServerAuthenticationSuccessHandler
import com.yellwsunn.authservice.security.oauth2.CustomServerSecurityContextRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.server.SecurityWebFilterChain

@Configuration
@EnableWebFluxSecurity
class SecurityConfig {
    @Bean
    fun securityFilterChain(
        http: ServerHttpSecurity,
        customServerSecurityContextRepository: CustomServerSecurityContextRepository,
        authenticationSuccessHandler: CustomRedirectServerAuthenticationSuccessHandler,
    ): SecurityWebFilterChain {
        return http
            .authorizeExchange { it.anyExchange().authenticated() }
            .oauth2Login { it.authenticationSuccessHandler(authenticationSuccessHandler) }
            .securityContextRepository(customServerSecurityContextRepository)
            .build()
    }
}

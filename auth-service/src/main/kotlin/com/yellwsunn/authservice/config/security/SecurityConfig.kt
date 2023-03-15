package com.yellwsunn.authservice.config.security

import com.yellwsunn.authservice.config.security.oauth2.CustomRedirectServerAuthenticationSuccessHandler
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
        authenticationSuccessHandler: CustomRedirectServerAuthenticationSuccessHandler,
    ): SecurityWebFilterChain {
        return http
            .authorizeExchange {
                it.pathMatchers("/actuator/**").permitAll()
                    .anyExchange().authenticated()
            }
            .oauth2Login { it.authenticationSuccessHandler(authenticationSuccessHandler) }
            .build()
    }
}

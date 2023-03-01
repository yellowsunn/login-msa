package com.yellwsunn.authservice.security.oauth2

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContext
import org.springframework.security.web.server.context.ServerSecurityContextRepository
import org.springframework.security.web.server.context.WebSessionServerSecurityContextRepository
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono

@Component
class CustomServerSecurityContextRepository : ServerSecurityContextRepository {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    private val delegate = WebSessionServerSecurityContextRepository()

    override fun save(exchange: ServerWebExchange, context: SecurityContext?): Mono<Void> {
        return delegate.save(exchange, context)
    }

    override fun load(exchange: ServerWebExchange): Mono<SecurityContext> {
        return delegate.load(exchange)
//        return Mono.just(
//            SecurityContextImpl(
//                UsernamePasswordAuthenticationToken(
//                    "",
//                    "",
//                    listOf(SimpleGrantedAuthority(Role.USER.key))
//                )
//            )
//        )
    }
}

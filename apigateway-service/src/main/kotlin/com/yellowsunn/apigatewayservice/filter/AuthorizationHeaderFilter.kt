package com.yellowsunn.apigatewayservice.filter

import com.yellowsunn.apigatewayservice.domain.AccessTokenPayload
import com.yellowsunn.apigatewayservice.domain.AccessTokenService
import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.server.reactive.ServerHttpRequest
import org.springframework.stereotype.Component

@Component
class AuthorizationHeaderFilter(
    val accessTokenService: AccessTokenService,
) : AbstractGatewayFilterFactory<Any>() {
    companion object {
        private const val USER_ID_HEADER = "X_USER_ID"
    }

    override fun apply(config: Any?) = GatewayFilter { exchange, chain ->
        val request: ServerHttpRequest = exchange.request

        val authorizationHeader: String? = request.headers[HttpHeaders.AUTHORIZATION]?.getOrNull(0)
        val userId: String = if (authorizationHeader.isNullOrBlank()) {
            ""
        } else {
            val jwt = authorizationHeader.replace("bearer", "", true)
            val accessTokenPayload: AccessTokenPayload = accessTokenService.parseToken(jwt)
            accessTokenPayload.userId
        }

        val changedRequest = exchange.request.mutate()
            .header(USER_ID_HEADER, userId)
            .build()

        chain.filter(exchange.mutate().request(changedRequest).build())
    }
}

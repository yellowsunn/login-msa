package com.yellowsunn.apigatewayservice.filter

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.http.HttpHeaders
import org.springframework.http.ResponseCookie
import org.springframework.stereotype.Component

@Component
class OAuth2CallbackUrlFilter : AbstractGatewayFilterFactory<Any>() {
    companion object {
        private const val CALLBACK_URL_PARAM = "callback_url"
    }

    override fun apply(config: Any?) = GatewayFilter { exchange, chain ->
        val callbackUrl: String? = exchange.request.queryParams[CALLBACK_URL_PARAM]?.firstOrNull()
        if (!callbackUrl.isNullOrBlank()) {
            val cookie = ResponseCookie.from(CALLBACK_URL_PARAM.uppercase(), callbackUrl)
                .path("/")
                .httpOnly(true)
                .build()
            exchange.response.headers.add(HttpHeaders.SET_COOKIE, cookie.toString())
        }
        chain.filter(exchange)
    }
}

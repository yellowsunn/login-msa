package com.yellwsunn.authservice.security.oauth2

import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.DefaultServerRedirectStrategy
import org.springframework.security.web.server.ServerRedirectStrategy
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import java.net.URI

@Component
class CustomRedirectServerAuthenticationSuccessHandler : RedirectServerAuthenticationSuccessHandler() {
    companion object {
        private const val CALLBACK_URL = "CALLBACK_URL"
        private const val REDIRECT_TOKEN_PARAM = "token"
    }

    private val redirectStrategy: ServerRedirectStrategy = DefaultServerRedirectStrategy()

    override fun onAuthenticationSuccess(
        webFilterExchange: WebFilterExchange,
        authentication: Authentication,
    ): Mono<Void> {
        return mono { onAuthenticationSuccessCoroutineScope(webFilterExchange) }
    }

    private suspend fun onAuthenticationSuccessCoroutineScope(
        webFilterExchange: WebFilterExchange,
    ): Void? {
        val cookies = webFilterExchange.exchange.request.cookies
        val callbackUrl: String? = cookies[CALLBACK_URL]?.firstOrNull()?.value
        if (callbackUrl.isNullOrBlank()) {
            return null
        }

        val uri: URI = UriComponentsBuilder.fromHttpUrl(callbackUrl)
            .queryParam(REDIRECT_TOKEN_PARAM, "jwt-token-value")
            .build()
            .toUri()

        return this.redirectStrategy.sendRedirect(webFilterExchange.exchange, uri)
            .awaitSingleOrNull()
    }
}

package com.yellwsunn.authservice.config.security.oauth2

import com.yellowsunn.common.auth.accesstoken.AccessTokenHandler
import com.yellwsunn.authservice.config.security.oauth2.dto.CustomOAuth2User
import kotlinx.coroutines.reactor.awaitSingleOrNull
import kotlinx.coroutines.reactor.mono
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.server.DefaultServerRedirectStrategy
import org.springframework.security.web.server.ServerRedirectStrategy
import org.springframework.security.web.server.WebFilterExchange
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono
import java.net.URI

@Component
class CustomRedirectServerAuthenticationSuccessHandler(
    private val accessTokenHandler: AccessTokenHandler,
) : ServerAuthenticationSuccessHandler {
    companion object {
        private const val CALLBACK_URL = "CALLBACK_URL"
        private const val REDIRECT_TOKEN_PARAM = "token"
    }

    private val logger: Logger = LoggerFactory.getLogger(this::class.java)
    private val redirectStrategy: ServerRedirectStrategy = DefaultServerRedirectStrategy()
    private val defaultLocation = URI.create("/")

    override fun onAuthenticationSuccess(
        webFilterExchange: WebFilterExchange,
        authentication: Authentication,
    ): Mono<Void> {
        return mono { onAuthenticationSuccessCoroutineScope(webFilterExchange, authentication) }
    }

    private suspend fun onAuthenticationSuccessCoroutineScope(
        webFilterExchange: WebFilterExchange,
        authentication: Authentication,
    ): Void? {
        val cookies = webFilterExchange.exchange.request.cookies
        val callbackUrl: String? = cookies[CALLBACK_URL]?.firstOrNull()?.value

        if (callbackUrl.isNullOrBlank()) {
            logger.warn("callbackUrl이 비어있으므로 엑세스 토큰을 발행할 수 없습니다.")
            return redirectStrategy.sendRedirect(webFilterExchange.exchange, defaultLocation)
                .awaitSingleOrNull()
        }

        val customOAuth2User: CustomOAuth2User = authentication.principal as CustomOAuth2User
        val accessToken: String = accessTokenHandler.generateToken(
            customOAuth2User.convertToAccessTokenPayload()
        )

        val callbackTokenUri: URI = UriComponentsBuilder.fromHttpUrl(callbackUrl)
            .queryParam(REDIRECT_TOKEN_PARAM, accessToken)
            .build()
            .toUri()

        return this.redirectStrategy.sendRedirect(webFilterExchange.exchange, callbackTokenUri)
            .awaitSingleOrNull()
    }
}

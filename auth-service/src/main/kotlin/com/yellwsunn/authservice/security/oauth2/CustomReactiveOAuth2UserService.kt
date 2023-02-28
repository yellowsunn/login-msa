package com.yellwsunn.authservice.security.oauth2

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultReactiveOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService
import org.springframework.security.oauth2.core.user.DefaultOAuth2User
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomReactiveOAuth2UserService : ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> {
    override fun loadUser(userRequest: OAuth2UserRequest): Mono<OAuth2User> {
        return mono { loadUserCoroutineScope(userRequest) }
    }

    private suspend fun loadUserCoroutineScope(userRequest: OAuth2UserRequest): OAuth2User = coroutineScope {
        val delegate = DefaultReactiveOAuth2UserService()
        val oAuth2User: OAuth2User = delegate.loadUser(userRequest).awaitSingle()

        val userNameAttributeName =
            userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName

        DefaultOAuth2User(
            listOf(SimpleGrantedAuthority(Role.USER.key)),
            oAuth2User.attributes,
            userNameAttributeName,
        )
    }
}

package com.yellwsunn.authservice.config.security.oauth2

import com.yellwsunn.authservice.domain.user.User
import com.yellwsunn.authservice.domain.user.UserReactiveRepository
import com.yellwsunn.authservice.domain.user.command.UserSaveCommand
import com.yellwsunn.authservice.config.security.oauth2.dto.CustomOAuth2User
import com.yellwsunn.authservice.config.security.oauth2.dto.OAuth2Attributes
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.mono
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.userinfo.DefaultReactiveOAuth2UserService
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService
import org.springframework.security.oauth2.core.user.OAuth2User
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class CustomReactiveOAuth2UserService(
    private val userReactiveRepository: UserReactiveRepository,
    private val delegate: DefaultReactiveOAuth2UserService = DefaultReactiveOAuth2UserService(),
) : ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> {

    override fun loadUser(userRequest: OAuth2UserRequest): Mono<OAuth2User> {
        return mono { loadUserCoroutineScope(userRequest) }
    }

    private suspend fun loadUserCoroutineScope(userRequest: OAuth2UserRequest): OAuth2User = coroutineScope {
        val oAuth2User: OAuth2User = delegate.loadUser(userRequest).awaitSingle()
        val registrationId = userRequest.clientRegistration.registrationId
        val userNameAttributeName =
            userRequest.clientRegistration.providerDetails.userInfoEndpoint.userNameAttributeName

        val attributes: OAuth2Attributes =
            OAuth2Attributes.of(registrationId, userNameAttributeName, oAuth2User.attributes)

        val user = saveIfNotExistUser(registrationId, attributes)

        CustomOAuth2User(
            authorities = listOf(SimpleGrantedAuthority(user.role)),
            attributes = attributes.attributes,
            nameAttributeKey = userNameAttributeName,
            userId = user.userId,
            email = user.email
        )
    }

    private suspend fun saveIfNotExistUser(registrationId: String, attributes: OAuth2Attributes): User {
        val findUser: User? = userReactiveRepository.findUserByEmail(attributes.email)
        if (findUser != null) {
            return findUser
        }
        val command = UserSaveCommand(
            email = attributes.email,
            registrationId = registrationId,
            thumbnailImage = attributes.picture,
        )
        return userReactiveRepository.saveUser(command)
    }
}

package com.yellwsunn.authservice.config.security.oauth2.dto

import com.yellowsunn.common.auth.accesstoken.AccessTokenPayload
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.user.DefaultOAuth2User

class CustomOAuth2User(
    authorities: Collection<GrantedAuthority>,
    attributes: Map<String, Any>,
    nameAttributeKey: String,
    val userId: String,
    val email: String,
) : DefaultOAuth2User(
    authorities,
    attributes,
    nameAttributeKey,
) {
    fun convertToAccessTokenPayload(): AccessTokenPayload {
        return AccessTokenPayload(
            this.userId,
            this.email
        )
    }
}

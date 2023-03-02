package com.yellwsunn.authservice.security.oauth2

import com.yellwsunn.authservice.dto.AccessTokenPayload
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
            userId = this.userId,
            email = this.email
        )
    }
}

package com.yellwsunn.authservice.config.security.oauth2.dto

data class OAuth2Attributes(
    val attributes: Map<String, Any>,
    val nameAttributeKey: String,
    val name: String,
    val email: String,
    val picture: String?,
) {
    companion object {
        fun of(
            registrationId: String,
            userNameAttributeName: String,
            attributes: Map<String, Any>,
        ): OAuth2Attributes =
            ofGoogle(userNameAttributeName, attributes)

        private fun ofGoogle(
            userNameAttributeName: String,
            attributes: Map<String, Any>,
        ): OAuth2Attributes =
            OAuth2Attributes(
                name = attributes["name"] as String,
                email = attributes["email"] as String,
                picture = attributes["picture"] as String?,
                attributes = attributes,
                nameAttributeKey = userNameAttributeName,
            )
    }
}

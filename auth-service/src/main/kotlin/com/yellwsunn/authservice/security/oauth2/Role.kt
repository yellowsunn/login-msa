package com.yellwsunn.authservice.security.oauth2

enum class Role(
    val key: String,
    val title: String,
) {
    USER("ROLE_USER", "일반 사용자"),
}

package com.yellwsunn.authservice.domain.token

data class AccessTokenPayload(
    val userId: String,
    val email: String,
)

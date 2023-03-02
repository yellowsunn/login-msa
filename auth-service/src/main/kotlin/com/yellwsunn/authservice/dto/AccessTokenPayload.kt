package com.yellwsunn.authservice.dto

data class AccessTokenPayload(
    val userId: String,
    val email: String,
)

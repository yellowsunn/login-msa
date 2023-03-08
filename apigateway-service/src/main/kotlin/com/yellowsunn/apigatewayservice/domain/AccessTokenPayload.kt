package com.yellowsunn.apigatewayservice.domain

data class AccessTokenPayload(
    val userId: String,
    val email: String,
)

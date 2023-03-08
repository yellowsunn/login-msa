package com.yellowsunn.apigatewayservice.domain

interface AccessTokenService {
    fun parseToken(token: String): AccessTokenPayload
}

package com.yellwsunn.authservice.domain.token

interface AccessTokenService {
    fun generateToken(accessTokenPayload: AccessTokenPayload): String
    fun parseToken(token: String): AccessTokenPayload
}

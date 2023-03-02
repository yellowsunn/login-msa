package com.yellwsunn.authservice.service

import com.yellwsunn.authservice.dto.AccessTokenPayload

interface AccessTokenService {
    fun generateToken(accessTokenPayload: AccessTokenPayload): String
    fun parseToken(token: String): AccessTokenPayload
}

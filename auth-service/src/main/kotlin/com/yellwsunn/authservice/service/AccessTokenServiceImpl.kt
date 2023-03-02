package com.yellwsunn.authservice.service

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.yellwsunn.authservice.dto.AccessTokenPayload
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.time.Duration
import java.time.Instant
import java.util.*

@Component
class AccessTokenServiceImpl(
    @Value("\${access-token.secret}") private val secret: String,
    @Value("\${access-token.expiration}") private val expiration: Duration,
) : AccessTokenService {
    private val objectMapper: ObjectMapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    override fun generateToken(accessTokenPayload: AccessTokenPayload): String {
        val now = Instant.now()

        val payload: Map<String, Any> = jacksonObjectMapper()
            .convertValue(accessTokenPayload, object : TypeReference<Map<String, Any>>() {})

        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setClaims(payload)
            .setIssuedAt(Date.from(now))
            .setExpiration(Date.from(now.plus(expiration)))
            .signWith(Keys.hmacShaKeyFor(secret.toByteArray()), SignatureAlgorithm.HS256)
            .compact()
    }

    override fun parseToken(token: String): AccessTokenPayload {
        val jwtParSer: JwtParser = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secret.toByteArray()))
            .build()

        val claims: Jws<Claims> = jwtParSer.parseClaimsJws(token)

        val payload: String = objectMapper.writeValueAsString(claims.body)
        return objectMapper.readValue(payload, AccessTokenPayload::class.java)
    }
}

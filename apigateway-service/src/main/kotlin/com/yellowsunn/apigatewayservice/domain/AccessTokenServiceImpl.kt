package com.yellowsunn.apigatewayservice.domain

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.JwtParser
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class AccessTokenServiceImpl(
    @Value("\${access-token.secret}") private val secret: String,
) : AccessTokenService {
    private val objectMapper: ObjectMapper = jacksonObjectMapper()
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

    override fun parseToken(token: String): AccessTokenPayload {
        val jwtParSer: JwtParser = Jwts.parserBuilder()
            .setSigningKey(Keys.hmacShaKeyFor(secret.toByteArray()))
            .build()

        val claims: Jws<Claims> = jwtParSer.parseClaimsJws(token)

        val payload: String = objectMapper.writeValueAsString(claims.body)
        return objectMapper.readValue(payload, AccessTokenPayload::class.java)
    }
}

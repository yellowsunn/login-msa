package com.yellowsunn.common.auth.accesstoken;

import com.fasterxml.jackson.core.type.TypeReference;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;

public class AccessTokenHandler extends AccessTokenParser {
    private final Duration expiration;

    public AccessTokenHandler(String secret, Duration expiration) {
        super(secret);
        this.expiration = expiration;
    }

    public String generateToken(AccessTokenPayload accessTokenPayload) {
        if (expiration == null) {
            throw new IllegalStateException("expiration must not be null.");
        }

        var now = Instant.now();
        var payload = this.objectMapper.convertValue(accessTokenPayload, new TypeReference<Map<String, Object>>() {
        });

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(payload)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(expiration)))
                .signWith(Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
                .compact();
    }
}

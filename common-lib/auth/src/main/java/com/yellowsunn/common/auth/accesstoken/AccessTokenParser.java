package com.yellowsunn.common.auth.accesstoken;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yellowsunn.common.auth.accesstoken.exception.JwtTokenParseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import java.nio.charset.StandardCharsets;

public class AccessTokenParser {
    protected final String secret;
    protected final ObjectMapper objectMapper;

    public AccessTokenParser(String secret) {
        this.secret = secret;
        this.objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public AccessTokenPayload parseToken(String token) {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)))
                .build();

        Jws<Claims> claims = jwtParser.parseClaimsJws(token);

        try {
            String payload = objectMapper.writeValueAsString(claims.getBody());
            return objectMapper.readValue(payload, AccessTokenPayload.class);
        } catch (Exception e) {
            throw new JwtTokenParseException(e);
        }
    }
}

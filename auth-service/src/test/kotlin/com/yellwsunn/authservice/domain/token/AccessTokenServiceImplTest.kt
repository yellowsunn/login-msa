package com.yellwsunn.authservice.domain.token

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Duration

class AccessTokenServiceImplTest {

    private val accessTokenService: AccessTokenService = AccessTokenServiceImpl(
        "test-secret-key-test-secret-key-test",
        Duration.ofSeconds(10),
    )

    @Test
    fun generate_and_parse_token() {
        val accessTokenPayload = AccessTokenPayload(
            userId = "4d74b253-8182-4c7f-b1bd-73dd1317e124",
            email = "test@example.com",
        )

        val token = accessTokenService.generateToken(accessTokenPayload)
        val parsedTokenPayload: AccessTokenPayload = accessTokenService.parseToken(token)

        assertThat(parsedTokenPayload.userId).isEqualTo(accessTokenPayload.userId)
        assertThat(parsedTokenPayload.email).isEqualTo(accessTokenPayload.email)
    }
}

package com.yellowsunn.common.auth.accesstoken;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class AccessTokenHandlerTest {
    AccessTokenHandler accessTokenHandler = new AccessTokenHandler(
            "test-secret-key-test-secret-key-test",
            Duration.ofSeconds(30)
    );

    @Test
    void generate_and_parse_token() {
        var accessTokenPayload = new AccessTokenPayload(
                "4d74b253-8182-4c7f-b1bd-73dd1317e124",
                "test@example.com"
        );

        String token = accessTokenHandler.generateToken(accessTokenPayload);
        AccessTokenPayload parsedTokenPayload = accessTokenHandler.parseToken(token);

        assertThat(parsedTokenPayload.userId()).isEqualTo(accessTokenPayload.userId());
        assertThat(parsedTokenPayload.email()).isEqualTo(accessTokenPayload.email());
    }
}

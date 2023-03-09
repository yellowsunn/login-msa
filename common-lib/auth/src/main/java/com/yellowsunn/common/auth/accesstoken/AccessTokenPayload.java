package com.yellowsunn.common.auth.accesstoken;

public record AccessTokenPayload(
        String userId,
        String email
) {
}

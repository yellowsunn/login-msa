package com.yellowsunn.common.auth.accesstoken.exception;

public class JwtTokenParseException extends RuntimeException {
    public JwtTokenParseException(Throwable cause) {
        super("Failed to parse jwt token", cause);
    }
}

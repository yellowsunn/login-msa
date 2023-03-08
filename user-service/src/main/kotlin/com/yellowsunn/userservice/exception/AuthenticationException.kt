package com.yellowsunn.userservice.exception

class AuthenticationException(
    message: String = "인증 정보를 찾을 수 없습니다.",
) : IllegalStateException(message)

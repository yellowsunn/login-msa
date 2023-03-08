package com.yellowsunn.userservice.exception

class UserNotFoundException(
    message: String = "회원을 찾을 수 없습니다",
) : IllegalArgumentException(message)

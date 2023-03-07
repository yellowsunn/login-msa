package com.yellowsunn.userservice.application.dto

import com.yellowsunn.userservice.domain.user.User

data class UserResponseDto(
    val userId: String,
    val email: String,
    val role: String,
) {
    constructor(user: User) : this(
        userId = user.userId,
        email = user.email,
        role = user.role.toString(),
    )
}

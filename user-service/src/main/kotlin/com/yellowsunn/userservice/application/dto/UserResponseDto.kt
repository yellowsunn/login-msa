package com.yellowsunn.userservice.application.dto

import com.yellowsunn.userservice.constant.UserProvider
import com.yellowsunn.userservice.constant.UserRole
import com.yellowsunn.userservice.domain.user.User

data class UserResponseDto(
    val pk: Long,
    val userId: String,
    val email: String,
    val role: UserRole,
    val thumbnailImage: String,
    val provider: UserProvider,
) {
    constructor(
        user: User,
    ) : this(
        pk = user.pk,
        userId = user.userId,
        email = user.email,
        role = user.role,
        thumbnailImage = user.thumbnailImage ?: "",
        provider = user.provider,
    )
}

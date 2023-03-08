package com.yellowsunn.userservice.application.dto

import com.yellowsunn.userservice.constant.UserProvider
import com.yellowsunn.userservice.domain.user.User

class ExternalUserResponseDto(
    val userId: String,
    val email: String,
    val thumbnailImage: String,
    val provider: UserProvider,
) {
    constructor(
        user: User,
    ) : this(
        userId = user.userId,
        email = user.email,
        thumbnailImage = user.thumbnailImage ?: "",
        provider = user.provider,
    )
}

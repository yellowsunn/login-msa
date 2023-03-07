package com.yellowsunn.userservice.application.dto

import com.yellowsunn.userservice.constant.UserProvider
import com.yellowsunn.userservice.domain.user.User

data class UserSaveRequestDto(
    val email: String,
    val provider: UserProvider,
    val thumbnailImage: String?,
) {
    fun convertToEntity(): User {
        return User(
            email = email,
            provider = provider,
            thumbnailImage = thumbnailImage,
        )
    }
}

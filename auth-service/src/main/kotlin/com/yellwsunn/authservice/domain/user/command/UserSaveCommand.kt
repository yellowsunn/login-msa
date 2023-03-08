package com.yellwsunn.authservice.domain.user.command

import com.yellwsunn.authservice.const.UserProvider

class UserSaveCommand(
    registrationId: String,
    val email: String,
    val thumbnailImage: String?,
) {
    val provider: UserProvider = UserProvider.findByRegistrationId(registrationId)
}

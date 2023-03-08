package com.yellwsunn.authservice.domain.user

import com.yellwsunn.authservice.domain.user.command.UserSaveCommand

interface UserReactiveRepository {
    suspend fun findUserByEmail(email: String): User?
    suspend fun saveUser(command: UserSaveCommand): User
}

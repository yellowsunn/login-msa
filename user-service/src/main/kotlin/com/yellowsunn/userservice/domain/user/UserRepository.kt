package com.yellowsunn.userservice.domain.user

interface UserRepository {
    fun findByUserId(userId: String): User?
    fun save(user: User): User
}

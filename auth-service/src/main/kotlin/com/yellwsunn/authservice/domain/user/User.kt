package com.yellwsunn.authservice.domain.user

data class User(
    val pk: Long,
    val userId: String,
    val email: String,
    val role: String,
    val thumbnailImage: String,
    val provider: String,
)

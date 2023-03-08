package com.yellwsunn.authservice.infrastructure.http

data class ResultResponse<T>(
    val data: T?,
    val success: Boolean,
    val message: String?,
)

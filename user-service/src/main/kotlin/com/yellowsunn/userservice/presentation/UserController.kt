package com.yellowsunn.userservice.presentation

import com.yellowsunn.userservice.application.UserService
import com.yellowsunn.userservice.application.dto.UserResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/api/v1/users/by-user-id/{userId}")
    fun getUserByUserId(@PathVariable userId: String): UserResponseDto {
        return userService.getUserByUserId(userId)
    }
}

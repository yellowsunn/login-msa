package com.yellowsunn.userservice.presentation

import com.yellowsunn.userservice.application.UserService
import com.yellowsunn.userservice.application.dto.UserResponseDto
import com.yellowsunn.userservice.application.dto.UserSaveRequestDto
import com.yellowsunn.userservice.application.dto.UserSaveResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {
    @GetMapping("/api/v1/users/by-user-id/{userId}")
    fun getUserByUserId(@PathVariable userId: String): ResultResponse<UserResponseDto> {
        return ResultResponse.ok(
            userService.getUserByUserId(userId)
        )
    }

    @PostMapping("/api/v1/users")
    fun saveUser(@RequestBody userSaveRequestDto: UserSaveRequestDto): ResultResponse<UserSaveResponseDto> {
        return ResultResponse.ok(
            userService.saveUser(userSaveRequestDto)
        )
    }
}

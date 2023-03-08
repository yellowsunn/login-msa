package com.yellowsunn.userservice.api.internal

import com.yellowsunn.common.protocol.ResultResponse
import com.yellowsunn.userservice.application.UserService
import com.yellowsunn.userservice.application.dto.UserSaveRequestDto
import com.yellowsunn.userservice.application.dto.UserResponseDto
import com.yellowsunn.userservice.exception.UserNotFoundException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userService: UserService,
) {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @GetMapping("/api/v1/users/by-user-id/{userId}")
    fun getUserByUserId(@PathVariable userId: String): ResultResponse<UserResponseDto> {
        return ResultResponse.ok(
            userService.getUserByUserId(userId)
        )
    }

    @GetMapping("/api/v1/users/by-email/{email}")
    fun getUserByEmail(@PathVariable email: String): ResultResponse<UserResponseDto> {
        return ResultResponse.ok(
            userService.getUserByEmail(email)
        )
    }

    @PostMapping("/api/v1/users")
    fun saveUser(@RequestBody userSaveRequestDto: UserSaveRequestDto): ResultResponse<UserResponseDto> {
        return ResultResponse.ok(
            userService.saveUser(userSaveRequestDto)
        )
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException::class)
    protected fun handleUserNotFoundException(e: UserNotFoundException): ResultResponse<Void> {
        return ResultResponse.fail(e.message)
    }
}

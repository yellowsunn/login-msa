package com.yellowsunn.userservice.api.external

import com.yellowsunn.userservice.api.ResultResponse
import com.yellowsunn.userservice.application.ExternalUserService
import com.yellowsunn.userservice.application.dto.ExternalUserResponseDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RestController

@RestController
class ExternalUserController(
    private val externalUserService: ExternalUserService,
) {
    @GetMapping("/external/api/v1/users/my-info")
    fun getUserByUserId(@RequestHeader("X_USER_ID") userId: String): ResultResponse<ExternalUserResponseDto> {
        return ResultResponse.ok(
            externalUserService.getUserByUserId(userId)
        )
    }
}

package com.yellowsunn.userservice.application

import com.yellowsunn.userservice.application.dto.ExternalUserResponseDto
import com.yellowsunn.userservice.domain.user.User
import com.yellowsunn.userservice.domain.user.UserRepository
import com.yellowsunn.userservice.exception.AuthenticationException
import com.yellowsunn.userservice.exception.UserNotFoundException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

// External, internal 중복 코드로 리팩토링 필요
@Service
class ExternalUserService(
    private val userRepository: UserRepository,
) {
    @Transactional(readOnly = true)
    fun getUserByUserId(userId: String): ExternalUserResponseDto {
        verifyUserIdNotBlank(userId)
        val user: User = (userRepository.findByUserId(userId)
            ?: throw UserNotFoundException())

        return ExternalUserResponseDto(user)
    }

    // 어노테이션으로 옮기는게 좋을 듯
    private fun verifyUserIdNotBlank(userId: String) {
        if (userId.isBlank()) {
            throw AuthenticationException()
        }
    }
}

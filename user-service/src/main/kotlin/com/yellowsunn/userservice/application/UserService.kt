package com.yellowsunn.userservice.application

import com.yellowsunn.userservice.application.dto.UserResponseDto
import com.yellowsunn.userservice.domain.user.User
import com.yellowsunn.userservice.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {

    @Transactional
    fun getUserByUserId(userId: String): UserResponseDto {
        val user: User = userRepository.findByUserId(userId)
            ?: throw IllegalArgumentException("회원을 찾을 수 없습니다. userId=$userId")

        return UserResponseDto(user)
    }
}

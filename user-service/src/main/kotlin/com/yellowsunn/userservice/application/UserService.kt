package com.yellowsunn.userservice.application

import com.yellowsunn.userservice.application.dto.UserResponseDto
import com.yellowsunn.userservice.application.dto.UserSaveRequestDto
import com.yellowsunn.userservice.application.dto.UserSaveResponseDto
import com.yellowsunn.userservice.domain.user.User
import com.yellowsunn.userservice.domain.user.UserRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    private val userRepository: UserRepository,
) {
    @Transactional(readOnly = true)
    fun getUserByUserId(userId: String): UserResponseDto {
        val user: User = userRepository.findByUserId(userId)
            ?: throw IllegalArgumentException("회원을 찾을 수 없습니다. userId=$userId")

        return UserResponseDto(user)
    }

    @Transactional
    fun saveUser(userSaveRequestDto: UserSaveRequestDto): UserSaveResponseDto {
        val user: User = userSaveRequestDto.convertToEntity()
        verifyValidEmail(user.email)
        val savedUser: User = userRepository.save(user)

        return UserSaveResponseDto(savedUser)
    }

    private fun verifyValidEmail(email: String) {
        val user = userRepository.findByEmail(email)
        if (user != null) {
            throw IllegalArgumentException("이미 존재하는 이메일입니다.")
        }
    }
}

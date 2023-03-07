package com.yellowsunn.userservice.application

import com.yellowsunn.userservice.application.dto.UserResponseDto
import com.yellowsunn.userservice.domain.user.Provider
import com.yellowsunn.userservice.domain.user.Role
import com.yellowsunn.userservice.domain.user.User
import com.yellowsunn.userservice.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.util.*

class UserServiceTest {
    private val mockUserRepository: UserRepository = mockk()

    private val userService: UserService = UserService(mockUserRepository)

    @Test
    fun should_ReturnUser_When_FoundUser() {
        val userId = UUID.randomUUID().toString()
        every { mockUserRepository.findByUserId(userId) } returns
                User(
                    userId = userId,
                    email = "test@email.com",
                    provider = Provider.GOOGLE,
                    role = Role.ROLE_USER,
                    thumbnailImage = "https://exmaple.com/thumbnail.jpg"
                )
        val userResponseDto: UserResponseDto = userService.getUserByUserId(userId)

        assertThat(userResponseDto.userId).isEqualTo(userId)
    }

    @Test
    fun should_ThrowException_When_InvalidUserId() {
        val userId = "invalid-userId"
        every { mockUserRepository.findByUserId(userId) } returns null

        assertThatThrownBy { userService.getUserByUserId(userId) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}

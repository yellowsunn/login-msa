package com.yellowsunn.userservice.application

import com.yellowsunn.userservice.application.dto.UserResponseDto
import com.yellowsunn.userservice.application.dto.UserSaveRequestDto
import com.yellowsunn.userservice.application.dto.UserSaveResponseDto
import com.yellowsunn.userservice.constant.UserProvider
import com.yellowsunn.userservice.constant.UserRole
import com.yellowsunn.userservice.domain.user.User
import com.yellowsunn.userservice.domain.user.UserRepository
import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

class UserServiceTest {
    private val mockUserRepository: UserRepository = mockk()

    private val userService: UserService = UserService(mockUserRepository)

    @Test
    fun should_ReturnUser_When_FoundUser() {
        val userId = "valid-userId"
        val user = User(
            email = "test@email.com",
            provider = UserProvider.GOOGLE,
            role = UserRole.ROLE_USER,
            thumbnailImage = "https://exmaple.com/thumbnail.jpg"
        )
        every { mockUserRepository.findByUserId(any()) } returns user
        val userResponseDto: UserResponseDto = userService.getUserByUserId(userId)

        assertThat(userResponseDto.email).isEqualTo(user.email)
    }

    @Test
    fun should_ThrowException_When_InvalidUserId() {
        val userId = "invalid-userId"
        every { mockUserRepository.findByUserId(userId) } returns null

        assertThatThrownBy { userService.getUserByUserId(userId) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }

    @Test
    fun should_ReturnUser_When_SaveUser() {
        val requestDto = UserSaveRequestDto(
            email = "test@email.com",
            provider = UserProvider.GOOGLE,
            thumbnailImage = "https://exmaple.com/thumbnail.jpg",
        )
        every { mockUserRepository.findByEmail(any()) } returns null
        every { mockUserRepository.save(any()) } returnsArgument 0

        val responseDto: UserSaveResponseDto = userService.saveUser(requestDto)

        assertThat(responseDto.email).isEqualTo(requestDto.email)
    }

    @Test
    fun should_ThrowException_When_AlreadyExistEmail() {
        val email = "test@email.com"
        val requestDto = UserSaveRequestDto(
            email = email,
            provider = UserProvider.GOOGLE,
            thumbnailImage = "https://exmaple.com/thumbnail.jpg",
        )
        every { mockUserRepository.findByEmail(email) } returns
                User(
                    email = email,
                    provider = requestDto.provider,
                    thumbnailImage = null,
                )

        assertThatThrownBy { userService.saveUser(requestDto) }
            .isInstanceOf(IllegalArgumentException::class.java)
    }
}

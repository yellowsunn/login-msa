package com.yellowsunn.userservice.infrastructure.persistence

import com.yellowsunn.userservice.domain.user.User
import com.yellowsunn.userservice.domain.user.UserRepository
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserRepository : UserRepository, JpaRepository<User, Long>

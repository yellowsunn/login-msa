package com.yellowsunn.userservice.domain.user

import com.yellowsunn.userservice.constant.UserProvider
import com.yellowsunn.userservice.constant.UserRole
import com.yellowsunn.userservice.domain.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.util.*

@Entity
@Table(name = "users")
class User(
    email: String,
    provider: UserProvider,
    role: UserRole = UserRole.ROLE_USER,
    thumbnailImage: String?,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val pk: Long = 0

    @Column(nullable = false)
    val userId: String = UUID.randomUUID().toString()

    @Column(nullable = false)
    val email: String = email

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var provider: UserProvider = provider
        private set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: UserRole = role
        private set

    var thumbnailImage: String? = thumbnailImage
        private set
}

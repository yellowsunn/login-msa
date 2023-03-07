package com.yellowsunn.userservice.domain.user

import com.yellowsunn.userservice.domain.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "users")
class User(
    userId: String ,
    email: String,
    provider: Provider,
    role: Role,
    thumbnailImage: String?,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val pk: Long? = null

    @Column(nullable = false)
    val userId: String = userId

    @Column(nullable = false)
    val email: String = email

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var provider: Provider = provider
        private set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: Role = role
        private set

    var thumbnailImage: String? = thumbnailImage
        private set
}

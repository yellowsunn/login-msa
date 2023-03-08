package com.yellwsunn.authservice.const

enum class UserProvider(
    private val registrationId: String,
) {
    GOOGLE("google"),
    UNKNOWN("unknown")
    ;

    companion object {
        fun findByRegistrationId(registrationId: String): UserProvider {
            return UserProvider.values()
                .find { it.registrationId == registrationId } ?: UNKNOWN
        }
    }
}

package com.yellwsunn.authservice.infrastructure.http

import com.yellwsunn.authservice.domain.user.User
import com.yellwsunn.authservice.domain.user.UserReactiveRepository
import com.yellwsunn.authservice.domain.user.command.UserSaveCommand
import com.yellwsunn.authservice.parameterizedTypeReference
import kotlinx.coroutines.reactor.awaitSingle
import kotlinx.coroutines.reactor.awaitSingleOrNull
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

@Component
class UserWebClient(
    private val webClient: WebClient,
    @Value("\${micro-services.user-endpoint}") private val userEndpoint: String,
) : UserReactiveRepository {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    override suspend fun findUserByEmail(email: String): User? {
        return webClient.get()
            .uri(
                UriComponentsBuilder.fromHttpUrl(userEndpoint)
                    .path("/api/v1/users/by-email/$email")
                    .build()
                    .toUri()
            )
            .retrieve()
            .onStatus({ it.isSameCodeAs(HttpStatus.NOT_FOUND) }) { Mono.empty() }
            .bodyToMono(parameterizedTypeReference<ResultResponse<User>>())
            .toData()
            .awaitSingleOrNull()
    }

    override suspend fun saveUser(command: UserSaveCommand): User {
        return webClient.post()
            .uri(
                UriComponentsBuilder
                    .fromHttpUrl(userEndpoint)
                    .path("/api/v1/users")
                    .build()
                    .toUri()
            )
            .bodyValue(command)
            .retrieve()
            .bodyToMono(parameterizedTypeReference<ResultResponse<User>>())
            .toData()
            .awaitSingle()
    }

    private fun <T> Mono<ResultResponse<T>>.toData(): Mono<T> {
        return this
            .doOnError { e -> logger.error("{}", e.message, e) }
            .doOnNext {
                if (!it.success) {
                    logger.error(it.message)
                }
            }
            .filter { it.success }
            .map { it.data!! }
    }
}

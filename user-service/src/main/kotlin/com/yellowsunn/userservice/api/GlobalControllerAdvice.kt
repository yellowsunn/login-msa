package com.yellowsunn.userservice.api

import com.yellowsunn.common.protocol.ResultResponse
import com.yellowsunn.userservice.exception.AuthenticationException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {
    private val logger: Logger = LoggerFactory.getLogger(this::class.java)

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException::class)
    protected fun handleIllegalArgumentException(e: IllegalArgumentException): ResultResponse<Void> {
        logger.warn("Invalid parameter. message={}", e.message, e)
        return ResultResponse.fail(e.message)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalStateException::class)
    protected fun handleIllegalStateException(e: IllegalStateException): ResultResponse<Void> {
        logger.warn("Invalid state. message={}", e.message, e)
        return ResultResponse.fail(e.message)
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResultResponse<Void> {
        logger.warn("Failed to parse http message. message={}", e.message, e)
        return ResultResponse.fail(e.message)
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException::class)
    protected fun handleAuthenticationException(e: AuthenticationException): ResultResponse<Void> {
        logger.warn("Authentication failed. message={}", e.message, e)
        return ResultResponse.fail(e.message)
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception::class)
    protected fun handleUnknownException(e: Exception): ResultResponse<Void> {
        logger.error("Unknown Exception. message={}", e.message, e)
        return ResultResponse.fail(e.message)
    }
}

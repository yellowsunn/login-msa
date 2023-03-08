package com.yellwsunn.authservice

import org.springframework.core.ParameterizedTypeReference

inline fun <reified T : Any> parameterizedTypeReference() = object : ParameterizedTypeReference<T>() {}

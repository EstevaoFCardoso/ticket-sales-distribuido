package com.session.controller.api.error

import org.springframework.http.HttpStatus

data class ErrorResponse(
    val status: HttpStatus,
    val message: String,
    val timestamp: Long = System.currentTimeMillis()
)

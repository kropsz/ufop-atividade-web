package com.kropsz.backend.exception.handler

import com.fasterxml.jackson.annotation.JsonInclude
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.validation.BindingResult

data class ErrorMessage(
    val path: String,
    val method: String,
    val status: Int,
    val statusText: String,
    val message: String,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val errors: Map<String, String>? = null
) {
    constructor(request: HttpServletRequest, status: HttpStatus, message: String) : this(
        path = request.requestURI,
        method = request.method,
        status = status.value(),
        statusText = status.reasonPhrase,
        message = message
    )

    constructor(request: HttpServletRequest, status: HttpStatus, message: String, result: BindingResult) : this(
        path = request.requestURI,
        method = request.method,
        status = status.value(),
        statusText = status.reasonPhrase,
        message = message,
        errors = result.fieldErrors.associate { it.field to (it.defaultMessage ?: "Unknown error") }    )
}
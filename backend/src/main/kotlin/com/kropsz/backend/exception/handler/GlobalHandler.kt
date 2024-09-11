package com.kropsz.backend.exception.handler

import com.kropsz.backend.exception.EntityNotFound
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.validation.BindingResult
import org.springframework.web.bind.MethodArgumentNotValidException

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalHandler {

    private val log = LoggerFactory.getLogger(GlobalHandler::class.java)

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun methodArgumentNotValidException(
        ex: MethodArgumentNotValidException,
        request: HttpServletRequest,
        result: BindingResult
    ): ResponseEntity<ErrorMessage> {
        log.error("Api Error - ", ex)
        return ResponseEntity
            .status(HttpStatus.UNPROCESSABLE_ENTITY)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ErrorMessage(request, HttpStatus.UNPROCESSABLE_ENTITY, "Campos Inválidos: ", result))
    }

    @ExceptionHandler(RuntimeException::class)
    fun unexpectedErrorException(
        ex: RuntimeException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorMessage> {
        log.error("Api Error - ", ex)
        return ResponseEntity
            .status(HttpStatus.INTERNAL_SERVER_ERROR)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ErrorMessage(request, HttpStatus.INTERNAL_SERVER_ERROR, ex.message ?: "Unexpected error"))
    }

    @ExceptionHandler(EntityNotFound::class)
    fun entityNotFoundException(
        ex: RuntimeException,
        request: HttpServletRequest
    ): ResponseEntity<ErrorMessage> {
        log.error("Api Error - ", ex)
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .contentType(MediaType.APPLICATION_JSON)
            .body(ErrorMessage(request, HttpStatus.NOT_FOUND, ex.message ?: "Unexpected error"))
    }
}
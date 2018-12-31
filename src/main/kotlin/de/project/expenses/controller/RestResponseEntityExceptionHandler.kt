package de.project.expenses.controller

import de.project.expenses.service.exceptions.NoSuchResourceException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(value = [NoSuchResourceException::class])
    protected fun handleConflict(ex: RuntimeException, request: WebRequest): ResponseEntity<Any> {
        val body = ResponseBodyWrapper(HttpStatus.NOT_FOUND.value(), ex.message)

        return handleExceptionInternal(ex, body,
                HttpHeaders(), HttpStatus.NOT_FOUND, request)
    }

    data class ResponseBodyWrapper(

        val status: Int,

        val message: String?

    )

}
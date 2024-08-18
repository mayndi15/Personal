package com.salus.exceptions

import org.springframework.http.HttpStatus

class SalusException(
    private val messages: Message,
    vararg vars: Any
) : Exception(messages.getReadableText(*vars)) {
    val code: Long
        get() = messages.code

    val httpStatus: HttpStatus
        get() = messages.httpStatus
}
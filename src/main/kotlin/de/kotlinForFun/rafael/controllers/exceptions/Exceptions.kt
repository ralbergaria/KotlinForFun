package de.kotlinForFun.rafael.controllers.exceptions

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.NOT_FOUND)
class NotFoundException(override var message: String) : RuntimeException(message)

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
class BadRequestException(override var message: String) : RuntimeException(message)

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
class InternalServerException(override var message: String) : RuntimeException(message)


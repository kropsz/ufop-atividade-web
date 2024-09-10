package com.kropsz.backend.exception

class EntityNotFound : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}
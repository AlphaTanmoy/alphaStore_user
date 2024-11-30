package com.alphaStore.user.error

class BadRequestExceptionThrowable(
    var errorMessage: String = ""
) : Throwable()
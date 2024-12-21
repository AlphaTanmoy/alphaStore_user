package com.alphaStore.user.error

class ForbiddenExceptionThrowable(
    var errorMessage: String = "",
    var code: Int? = null
) : Throwable()
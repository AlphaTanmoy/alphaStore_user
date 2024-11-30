package com.alphaStore.user.error

class UnAuthorizedExceptionThrowable(
    var errorMessage: String = "",
    var code: Int? = null
) : Throwable()
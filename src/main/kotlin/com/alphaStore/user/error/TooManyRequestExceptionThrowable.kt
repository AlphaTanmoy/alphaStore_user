package com.alphaStore.user.error

class TooManyRequestExceptionThrowable(
    var errorMessage: String = "",
) : Throwable()
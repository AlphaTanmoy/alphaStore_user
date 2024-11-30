package com.alphaStore.user.error

class GenericException(
    var errorMessage: String = "",
    var code: Int = 0
) : RuntimeException()
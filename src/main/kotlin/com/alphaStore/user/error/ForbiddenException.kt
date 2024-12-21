package com.alphaStore.user.error

class ForbiddenException(
    var errorMessage: String = "",
    var code: Int? = null
) : RuntimeException()
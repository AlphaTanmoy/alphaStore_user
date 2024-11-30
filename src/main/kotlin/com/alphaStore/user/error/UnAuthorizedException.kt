package com.alphaStore.user.error

class UnAuthorizedException(
    var errorMessage: String = "",
    var code: Int? = null
) : RuntimeException()
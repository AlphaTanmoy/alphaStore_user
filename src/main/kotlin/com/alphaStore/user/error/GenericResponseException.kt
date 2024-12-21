package com.alphaStore.user.error

import com.alphaStore.user.enums.ResponseType

class GenericResponseException(
    var code: Int? = null,
    var type: String? = null,
    override var message: String = "",
    var responseType: ResponseType = ResponseType.SUCCESS,
) : RuntimeException()

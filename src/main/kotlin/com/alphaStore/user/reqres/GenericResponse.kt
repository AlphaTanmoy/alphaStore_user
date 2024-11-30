package com.alphaStore.user.reqres

import com.alphaStore.user.enums.ResponseType
import com.fasterxml.jackson.annotation.JsonFilter
import java.io.Serializable

data class GenericResponse(
    var code: Int? = null,
    var type: String? = null,
    var message: String = "",
    var responseType: ResponseType = ResponseType.SUCCESS,
) : Serializable

@JsonFilter("genericResponseFilter")
class GenericResponseMixIn
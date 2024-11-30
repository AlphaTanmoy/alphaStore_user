package com.alphaStore.user.model

data class SendOtpResponse(
    var successFull: Boolean = false,
    var message: String = ""
)
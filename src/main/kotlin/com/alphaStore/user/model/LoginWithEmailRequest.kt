package com.alphaStore.user.model

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class LoginWithEmailRequest(
    @field:NotNull(message = "Please provide email id")
    @field:NotBlank(message = "Please provide valid email id")
    @field:Email(
        message = "Email is not valid",
        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    )
    var emailId: String = "",
    @field:NotNull(message = "Please provide password")
    @field:NotBlank(message = "Please provide valid password")
    var password: String = "",
)

package com.alphaStore.user.model

import jakarta.validation.constraints.*

class UserCreateRequest (
    @field:NotNull(message = "Name Can't be Empty")
    @field:NotBlank(message = "Please provide valid name")
    var userName: String = "",

    @field:NotBlank(message = "user address must not be blank")
    var userAddress: String = "",

    @field:NotNull(message = "Email id Can't be Empty")
    @field:NotBlank(message = "Please provide valid email id")
    @field:Email(
        message = "Email is not valid",
        regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"
    )
    var userEmail: String = "",

    @field:NotNull(message = "Password can't be empty")
    @field:NotBlank(message = "Password must not be blank")
    @field:Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters long")
    @field:Pattern(
        regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
        message = "Password must contain at least one digit, one lowercase letter, one uppercase letter, and one special character"
    )
    var userPassword: String = "",

    @field:NotNull(message = "user phone number Can't be Empty")
    @field:NotBlank(message = "user phone number must not be blank")
    @field:Pattern(
        regexp = "\\d{10,15}",
        message = "user phone number must be between 10 and 15 digits"
    )
    var userPhone: String = "",

    @field:NotNull(message = "user country Can't be Empty")
    @field:NotBlank(message = "user country must not be blank")
    var userCountry: String = "",
)
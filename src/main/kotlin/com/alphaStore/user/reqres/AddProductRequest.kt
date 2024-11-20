package com.alphaStore.user.reqres

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class AddProductRequest(
    @field:NotNull(message = "Please provide productName")
    @field:NotBlank(message = "Please provide productName")
    var productName: String = "",
    @field:NotNull(message = "Please provide productName")
    @field:NotBlank(message = "Please provide productName")
    var productPrice: Long = 0,
    @field:NotNull(message = "Please provide numberOfProductsPresentAtStore")
    @field:NotBlank(message = "Please provide numberOfProductsPresentAtStore")
    var numberOfProductsPresentAtStore: Long = 0,
)
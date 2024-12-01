package com.alphaStore.user.controller

import com.alphaStore.user.error.BadRequestException
import com.alphaStore.user.model.GetProfile
import com.alphaStore.user.model.PaginationResponse
import com.alphaStore.user.model.ProductResponse
import com.alphaStore.user.service.CountryService
import com.alphaStore.user.service.JWTTokenService
import com.alphaStore.user.service.UserService
import com.alphaStore.user.utils.JwtUtilMaster
import com.alphaStore.user.utils.KeywordsAndConstants.HEADER_AUTHORIZATION
import com.alphaStore.user.utils.password.PasswordEncoderMaster
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestHeader

class UserController(
    private val userService: UserService,
    private val countryService: CountryService,
    private val jwtTokenService: JWTTokenService,
    private var passwordEncoderMaster: PasswordEncoderMaster,
    private var jwtUtilMaster: JwtUtilMaster
) {

    @PostMapping("/getProfile")
    fun getProfile(
        @Valid
        @NotBlank(message = "Please provide Authorization token")
        @RequestHeader(HEADER_AUTHORIZATION) token: String
    ): GetProfile {
        val userOptional = jwtUtilMaster.getUserFromToken(token)
        if (userOptional.isEmpty) {
            throw BadRequestException(
                errorMessage = "Failed to parse data"
            )
        } else {
            val user = userOptional.get()
            return userService.convertUserToGetProfile(user)
        }
    }

    @GetMapping("/view/{merchantId}")
    fun viewProduct() : PaginationResponse<ProductResponse> {
        val productResponse = userService.viewProduct()
        return PaginationResponse(productResponse.data)
    }

}
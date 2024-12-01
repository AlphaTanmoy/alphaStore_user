package com.alphaStore.user.controller

import com.alphaStore.user.entity.User
import com.alphaStore.user.enums.UserType
import com.alphaStore.user.error.BadRequestException
import com.alphaStore.user.model.*
import com.alphaStore.user.service.CountryService
import com.alphaStore.user.service.JWTTokenService
import com.alphaStore.user.service.UserService
import com.alphaStore.user.utils.JwtUtilMaster
import com.alphaStore.user.utils.KeywordsAndConstants.HEADER_AUTHORIZATION
import com.alphaStore.user.utils.password.PasswordEncoderMaster
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/user")
class UserController(
    private val userService: UserService,
    private val countryService: CountryService,
    private val jwtTokenService: JWTTokenService,
    private var passwordEncoderMaster: PasswordEncoderMaster,
    private var jwtUtilMaster: JwtUtilMaster
) {

    @PostMapping("/login/email")
    fun loginWithEmailId(
        @RequestBody loginWithEmailRequest: LoginWithEmailRequest
    ): TokenCreationResponse {
        if (loginWithEmailRequest.emailId.isEmpty()) {
            throw BadRequestException("Please provide email id")
        }
        if (!loginWithEmailRequest.emailId.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex())) {
            throw BadRequestException("Email not valid")
        }
        if (loginWithEmailRequest.password.isEmpty()) {
            throw BadRequestException("Please provide password")
        }

        val user = userService.loginUsingEmailId(
            loginWithEmailRequest.emailId,
            loginWithEmailRequest.password,
            loginWithEmailRequest
        )

        return jwtTokenService.generateToken(
            user = user,
            isForLogin = true
        )
    }

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

    @GetMapping("/viewProduct")
    fun viewProduct() : PaginationResponse<ProductResponse> {
        val productResponse = userService.viewProduct()
        return PaginationResponse(productResponse.data)
    }

    @PostMapping("/create")
    fun createMerchant(
        @RequestBody newUserRequest: UserCreateRequest
    ): User {

        if (newUserRequest.userName.isEmpty()) {
            throw BadRequestException("Please provide valid name")
        }

        if (newUserRequest.userEmail.isEmpty()) {
            throw BadRequestException("Please provide email id")
        }

        if (!newUserRequest.userEmail.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$".toRegex())) {
            throw BadRequestException("Email not valid")
        }

        if (newUserRequest.userAddress.isEmpty()){
            throw BadRequestException("Please provide address")
        }

        if (newUserRequest.userAddress.isEmpty()) {
            throw BadRequestException("Please provide an address")
        }

        if (newUserRequest.userAddress.length < 7 || newUserRequest.userAddress.isBlank()) {
            throw BadRequestException("Address must be at least 10 characters long and not blank")
        }

        if (!Regex("\\d{10,15}").matches(newUserRequest.userPhone)) {
            throw BadRequestException(errorMessage = "Merchant phone number must be between 10 and 15 digits.")
        }

        if (!newUserRequest.userPassword.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}$".toRegex())) {
            throw BadRequestException("Password must be at least 8 characters long and include uppercase, lowercase, a number, and a special character")
        }

        val findCountryByKnownName = countryService.getCountryByKnownName(newUserRequest.userCountry)
            ?: throw BadRequestException(errorMessage = "Country Name Not exists.")

        val userToReturn = User(
            name = newUserRequest.userName,
            phone = newUserRequest.userPhone,
            emailId = newUserRequest.userEmail,
            userType = UserType.USER,
            password = passwordEncoderMaster.encode(newUserRequest.userPassword),
            country = findCountryByKnownName.knownName,
            address = newUserRequest.userAddress,
        )

        return userService.createUser(
            user = userToReturn
        )
    }
    
    
}
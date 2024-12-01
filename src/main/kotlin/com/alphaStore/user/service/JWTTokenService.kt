package com.alphaStore.user.service

import com.alphaStore.user.entity.User
import com.alphaStore.user.model.TokenCreationResponse
import com.alphaStore.user.utils.JwtUtilMaster
import org.springframework.stereotype.Component

@Component
class JWTTokenService (
    private val jwtUtilMaster: JwtUtilMaster
){
    fun generateToken(
        user: User? = null,
        trackingId: String? = null,
        isForLogin: Boolean = false
    ): TokenCreationResponse {
        var tokenCreationResponse = TokenCreationResponse()
        user?.let { userData ->
            tokenCreationResponse = jwtUtilMaster.prepareJWT(
                userData.id,
                userData.userType,
                trackingId
            )
        }
        return tokenCreationResponse
    }

}
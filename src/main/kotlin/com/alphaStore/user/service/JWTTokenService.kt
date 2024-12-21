package com.alphaStore.user.service

import com.alphaStore.user.entity.User
import com.alphaStore.user.enums.AccessRole
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
        isForLogin: Boolean = false,
        accessRole: AccessRole = AccessRole.USER
    ): TokenCreationResponse {
        var tokenCreationResponse = TokenCreationResponse()
        user?.let { userData ->
            tokenCreationResponse = jwtUtilMaster.prepareJWT(
                id= userData.id,
                userType = userData.userType,
                accessRole = accessRole,
                trackingId = trackingId,
            )
        }
        return tokenCreationResponse
    }

}
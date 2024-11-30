package com.alphaStore.user.model

import java.io.Serializable
import java.time.ZonedDateTime

data class GetProfile(
    var userId: String = "",
    var userName: String = "",
    var userEmail: String = "",
    var userPhone: String = "",
    var userAddress: String = "",
    var userCountry: String = "",
    var userCreationDate: ZonedDateTime
) : Serializable
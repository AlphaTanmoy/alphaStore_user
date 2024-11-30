package com.alphaStore.user.entity

import com.alphaStore.user.entity.superentity.SuperEntityWithIdCreatedLastModifiedDataStatus
import com.alphaStore.user.enums.OtpRequiredFor
import com.alphaStore.user.enums.UserType
import com.fasterxml.jackson.annotation.JsonFilter
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import java.time.ZonedDateTime

@Entity(name = "otps")
data class Otp(
    var otp: String = "",
    var expiry: ZonedDateTime = ZonedDateTime.now(),
    var userType: UserType = UserType.MERCHANT,
    var merchantId: String = "",
    @Enumerated(EnumType.STRING)
    var otpRequiredFor: OtpRequiredFor = OtpRequiredFor.LOGIN,
    var numberOfTimesOtpVerificationBeenTried: Int = 1,
) : SuperEntityWithIdCreatedLastModifiedDataStatus()

@JsonFilter("OtpFilter")
class OtpMixIn
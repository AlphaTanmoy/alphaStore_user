package com.alphaStore.user.service

import com.alphaStore.user.contract.aggregator.UserRepoAggregator
import com.alphaStore.user.entity.User
import com.alphaStore.user.enums.OtpDeliveryChannel
import com.alphaStore.user.enums.OtpRequiredFor
import com.alphaStore.user.enums.OtpVerificationResult
import com.alphaStore.user.enums.UserType
import com.alphaStore.user.error.BadRequestException
import com.alphaStore.user.model.SendOtpResponse
import com.alphaStore.user.utils.otp.OTPMaster
import org.springframework.stereotype.Component

@Component
class OtpVerificationService (
    private val otpMaster: OTPMaster,
    private val userRepoAggregator: UserRepoAggregator,
){

    fun sendOtp(
        user: User,
        otpDeliveryChannel: OtpDeliveryChannel,
        otpRequiredFor: OtpRequiredFor,
    ): SendOtpResponse {

        if (otpDeliveryChannel == OtpDeliveryChannel.SYSTEM_GENERATED_NON_SENT && otpRequiredFor != OtpRequiredFor.IMPERSONATION) {
            throw BadRequestException("System generated otps can be only used for impersonation")
        }
        otpMaster.canSendOtp(user.id,)
        return otpMaster.sendOtp(
            emailId = user.emailId,
            usertype = UserType.USER,
            userId = user.id,
            otpDeliveryChannel = otpDeliveryChannel,
            otpRequiredFor = otpRequiredFor,
        )
    }
    fun verifyOtpForForgotPassword(
        otpDeliveryChannel: OtpDeliveryChannel,
        otpRequiredFor: OtpRequiredFor,
        user: User,
        otp: String,
    ) {
        val userId = user.id
        val otpVerificationResult = otpMaster.verifyOtp(
            otpRequiredFor = OtpRequiredFor.FORGOT_PASSWORD,
            otpDeliveryChannel = otpDeliveryChannel,
            userId = userId,
            otp = otp
        )
        if (otpVerificationResult != OtpVerificationResult.VERIFIED) {
            throw BadRequestException(errorMessage = otpVerificationResult.nameDescriptor)
        }
        user.let {
            if (otpDeliveryChannel == OtpDeliveryChannel.SERVER_GENERATED_EMAIL_DELIVERED_OTP) {
                userRepoAggregator.save(user)
            }
        }

    }


}
package com.alphaStore.user.utils.otp

import com.alphaStore.user.contract.aggregator.OtpRepoAggregator
import com.alphaStore.user.entity.Otp
import com.alphaStore.user.enums.*
import com.alphaStore.user.error.BadRequestException
import com.alphaStore.user.model.SendOtpResponse
import com.alphaStore.user.utils.KeywordsAndConstants.CAN_SEND_OTP_THROUGH_EMAIL
import com.alphaStore.user.utils.KeywordsAndConstants.DELAY_IN_EACH_OTP_SENT_MINUTES
import com.alphaStore.user.utils.KeywordsAndConstants.MINUTES_TO_HALT_ON_RELATED_SEND_OTP_THRESHOLD_CROSSED
import com.alphaStore.user.utils.KeywordsAndConstants.NUMBER_OF_REPEATED_OTP_SEND_ATTEMPTS_BEFORE_HALTING
import com.alphaStore.user.utils.KeywordsAndConstants.NUMBER_OF_TIMES_OTP_VERIFICATION_ALLOWED_PER_OTP
import com.alphaStore.user.utils.KeywordsAndConstants.OTP_DIGITS
import com.alphaStore.user.utils.email.EmailMaster
import com.alphaStore.user.utils.emailTemplates.SendOtpTemplateCreator
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.time.ZonedDateTime
import java.time.temporal.ChronoUnit

@Component
class OTPMaster (
    private val otpRepoAggregator: OtpRepoAggregator,
    private val environment: Environment,
    private val tOtpMaster: TOtpMaster,
    private val emailMaster: EmailMaster
) {
    fun canSendOtp(
        merchantId: String,
    ) {
        val otps = otpRepoAggregator.findLastOtpsSentToUser(
            merchantId,
            NUMBER_OF_REPEATED_OTP_SEND_ATTEMPTS_BEFORE_HALTING
        )
        if (otps.isEmpty())
            return
        var countInThresholdTime = 0
        val latestOtp = otps[0]
        if (latestOtp.dataStatus == DataStatus.INACTIVE)
            return
        if (latestOtp.createdDate.until(
                ZonedDateTime.now(),
                ChronoUnit.MINUTES
            ) < DELAY_IN_EACH_OTP_SENT_MINUTES
        ) {
            throw BadRequestException(
                "Please wait for ${
                    (DELAY_IN_EACH_OTP_SENT_MINUTES * 60) - latestOtp.createdDate.until(
                        ZonedDateTime.now(),
                        ChronoUnit.SECONDS
                    )
                } seconds to resend otp."
            )
        }
        var atLeastOneOtpSuccessful = false
        otps.forEach { otp ->
            if (otp.dataStatus == DataStatus.INACTIVE)
                atLeastOneOtpSuccessful = true
        }
        if (
            !atLeastOneOtpSuccessful &&
            latestOtp.createdDate.until(
                ZonedDateTime.now(),
                ChronoUnit.MINUTES
            ) <
            MINUTES_TO_HALT_ON_RELATED_SEND_OTP_THRESHOLD_CROSSED
        ) {
            throw BadRequestException(
                "Please wait for ${
                    (MINUTES_TO_HALT_ON_RELATED_SEND_OTP_THRESHOLD_CROSSED) - latestOtp.createdDate.until(
                        ZonedDateTime.now(),
                        ChronoUnit.MINUTES
                    )
                } minutes to resend otp."
            )
        }
    }

    fun sendOtp(
        emailId: String = "",
        usertype: UserType,
        merchantId: String,
        otpDeliveryChannel: OtpDeliveryChannel,
        otpRequiredFor: OtpRequiredFor,
    ): SendOtpResponse {
        var isDev = false
        if (environment.activeProfiles.isEmpty())
            isDev = true
        else if (environment.activeProfiles[0] == "dev" || environment.activeProfiles[0] == "loc")
            isDev = true

        val otp = if (isDev) {
            var toReturn = ""
            for (i in 1..OTP_DIGITS) {
                toReturn = "${toReturn}0"
            }
            toReturn
        } else RandomOTPManager.getRandomOTP(OTP_DIGITS)

        val sendOtpResponse = SendOtpResponse()
        when (otpDeliveryChannel) {
            OtpDeliveryChannel.SERVER_GENERATED_EMAIL_DELIVERED_OTP -> {
                if (!CAN_SEND_OTP_THROUGH_EMAIL) {
                    throw BadRequestException("Sending otp through Email is disabled for now, Please try another method")
                }

                sendOtpResponse.successFull = true
            }


            OtpDeliveryChannel.SYSTEM_GENERATED_NON_SENT -> {
                sendOtpResponse.successFull = true
                sendOtpResponse.message = otp
            }

            else -> {
                throw BadRequestException("Failed to send OTP")
            }
        }
        otpRepoAggregator.save(
            Otp(
                otp = otp,
                expiry = ZonedDateTime.now().plusMinutes(DELAY_IN_EACH_OTP_SENT_MINUTES.toLong()),
                userType = UserType.USER,
                merchantId = merchantId,
                otpRequiredFor = otpRequiredFor
            )
        )

        val emailRecipients = arrayListOf(emailId)
        val templateCreatorMaster = SendOtpTemplateCreator(
            otp = otp,
            reason = otpRequiredFor.toString()
        )

        emailMaster.sendMail(
            emails = emailRecipients,
            useTemplate = true,
            templateCreatorMaster = templateCreatorMaster,
            sendEmailFrom = SendEmailFrom.SUPPORT
        )

        return sendOtpResponse
    }

    fun verifyOtp(
        otpRequiredFor: OtpRequiredFor,
        otpDeliveryChannel: OtpDeliveryChannel,
        merchantId: String,
        otp: String,
        tOtpSecret: String = "",
    ): OtpVerificationResult {
        var valid = false

        if (otpDeliveryChannel == OtpDeliveryChannel.CLIENT_GENERATED_TOTP) {
            val tOtpVerificationResult = tOtpMaster.verify(
                tOtpSecret,
                otp
            )
            return if (tOtpVerificationResult) OtpVerificationResult.VERIFIED else OtpVerificationResult.NOT_VALID
        }

        var verificationFrequencyCrossed = false
        val foundResults = ArrayList(
            otpRepoAggregator.findByMerchantIdAndOtpPurposeAndOtpAndDataStatus(
                merchantId, otpRequiredFor, otp
            )
        )
        var otpToDeActivate = Otp()
        foundResults.forEach {
            if (it.expiry.isAfter(ZonedDateTime.now())) {
                if (it.numberOfTimesOtpVerificationBeenTried >= NUMBER_OF_TIMES_OTP_VERIFICATION_ALLOWED_PER_OTP) {
                    verificationFrequencyCrossed = true
                } else {
                    valid = true
                    otpToDeActivate = it
                }
            }
        }
        if (valid) {
            otpToDeActivate.dataStatus = DataStatus.INACTIVE
            otpRepoAggregator.save(otpToDeActivate)
        }
        return if (verificationFrequencyCrossed)
            OtpVerificationResult.EXCEEDED_VERIFICATION_FREQUENCY
        else if (!valid)
            OtpVerificationResult.NOT_VALID
        else OtpVerificationResult.VERIFIED
    }
}
package com.alphaStore.user.enums

enum class OtpVerificationResult(val nameDescriptor: String) {
    VERIFIED("OTP is verified"),
    NOT_VALID("OTP not valid"),
    EXCEEDED_VERIFICATION_FREQUENCY("OTP expired dut to wrong otp verification multiple times")
}
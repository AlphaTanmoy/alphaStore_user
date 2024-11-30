package com.alphaStore.user.enums

enum class OtpDeliveryChannel(val nameDescriptor: String) {
    SERVER_GENERATED_EMAIL_DELIVERED_OTP("OTP over Email"),
    CLIENT_GENERATED_TOTP("Time based OTP"),
    SYSTEM_GENERATED_NON_SENT("OTP generated not sent"),
}
package com.alphaStore.user.enums

enum class OtpRequiredFor(val nameDescriptor: String) {
    LOGIN("login"),
    EMAIL_VERIFICATION("email verification"),
    RESET_PASSWORD("reset password"),
    FORGOT_PASSWORD("forgot password"),
    IMPERSONATION("Impersonation"),
}

package com.alphaStore.user.utils.otp

import dev.turingcomplete.kotlinonetimepassword.GoogleAuthenticator
import org.springframework.core.env.Environment
import org.springframework.stereotype.Component
import java.util.*

@Component
class TOtpMaster(
    private val environment: Environment
) {

    fun getTOtpSecret(): String {
        return String(GoogleAuthenticator.createRandomSecretAsByteArray())
    }

    fun getQRCodeString(secret: String, otpDigits: Int, userName: String): String {
        return "otpauth://totp/alphaStore(${userName})${getEnvIfNotProd()}?secret=$secret&algorithm=SHA1&digits=$otpDigits&period=30"
    }

    fun verify(secret: String, otp: String): Boolean {
        val otpCurrent = GoogleAuthenticator(secret.toByteArray()).generate(Date())
        return otpCurrent == otp
    }

    fun getEnvIfNotProd(): String {
        if (environment.activeProfiles.isEmpty())
            return " dev"
        when (environment.activeProfiles[0].lowercase()) {
            "dev" -> return " dev"
            "test" -> return " test"
            "prod" -> return ""
            "sandbox" -> return " sandbox"
        }
        return " dev"
    }

}
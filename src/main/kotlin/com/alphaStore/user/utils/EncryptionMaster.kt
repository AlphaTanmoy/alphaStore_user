package com.alphaStore.user.utils

import com.alphaStore.user.encryptionmaster.AESUtil
import com.alphaStore.user.error.BadRequestException
import org.springframework.stereotype.Component
import javax.crypto.SecretKey
import javax.crypto.spec.IvParameterSpec

@Component
class EncryptionMaster {

    fun encrypt(
        stringToEncrypt: String,
    ): String {
        try {
            val password = KeywordsAndConstants.ENCRYPTION_PASSWORD_CHOICE_ONE
            val salt = KeywordsAndConstants.ENCRYPTION_PASSWORD_SALT_CHOICE
            val ivParameterSpec: IvParameterSpec = AESUtil.generateIv()
            val key: SecretKey = AESUtil.getKeyFromPassword(password, salt)
            return AESUtil.encryptPasswordBased(stringToEncrypt, key, ivParameterSpec)
        } catch (ex: Exception) {
            throw BadRequestException()
        }
    }

    fun encrypt(
        stringToEncrypt: String,
        password: String,
        salt: String?
    ): String {
        try {
            val saltFinal = salt ?: KeywordsAndConstants.ENCRYPTION_PASSWORD_SALT_CHOICE
            val ivParameterSpec: IvParameterSpec = AESUtil.generateIv()
            val key: SecretKey = AESUtil.getKeyFromPassword(password, saltFinal)
            return AESUtil.encryptPasswordBased(stringToEncrypt, key, ivParameterSpec)
        } catch (ex: Exception) {
            throw BadRequestException()
        }
    }

    fun decrypt(
        toDecrypt: String?,
        tryOne: Boolean,
        tryTwo: Boolean,
        tryThree: Boolean
    ): String {
        return try {
            val password =
                if (tryOne)
                    KeywordsAndConstants.ENCRYPTION_PASSWORD_CHOICE_ONE
                else if (tryTwo)
                    KeywordsAndConstants.ENCRYPTION_PASSWORD_CHOICE_TWO
                else
                    KeywordsAndConstants.ENCRYPTION_PASSWORD_CHOICE_THREE
            val salt = KeywordsAndConstants.ENCRYPTION_PASSWORD_SALT_CHOICE
            val ivParameterSpec: IvParameterSpec = AESUtil.generateIv()
            val key: SecretKey = AESUtil.getKeyFromPassword(password, salt)
            AESUtil.decryptPasswordBased(
                toDecrypt, key, ivParameterSpec
            )
        } catch (ex: Exception) {
            return if (tryOne)
                decrypt(toDecrypt, tryOne = false, tryTwo = true, tryThree = false)
            else if (tryTwo)
                decrypt(toDecrypt, tryOne = false, tryTwo = false, tryThree = true)
            else
                throw BadRequestException()
        }
    }

    fun decryptWithPassword(
        toDecrypt: String, password: String, salt: String?
    ): String {
        return try {
            val saltFinal = salt ?: KeywordsAndConstants.ENCRYPTION_PASSWORD_SALT_CHOICE
            val ivParameterSpec: IvParameterSpec = AESUtil.generateIv()
            val key: SecretKey = AESUtil.getKeyFromPassword(password, saltFinal)
            AESUtil.decryptPasswordBased(
                toDecrypt, key, ivParameterSpec
            )
        } catch (ex: Exception) {
            throw BadRequestException()
        }
    }
}
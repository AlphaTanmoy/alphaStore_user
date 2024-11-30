package com.alphaStore.user.utils.otp

import kotlin.random.Random

object RandomOTPManager {

    fun getRandomOTP(length: Int): String {
        val CHAR_SET_09 = "0123456789".toCharArray()
        var toReturn = ""
        for (i in 1..length) {
            toReturn = "$toReturn${CHAR_SET_09[Random.nextInt(CHAR_SET_09.size)]}"
        }
        return toReturn
    }
}
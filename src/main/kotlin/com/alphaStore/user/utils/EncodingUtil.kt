package com.alphaStore.user.utils

import org.springframework.stereotype.Component
import java.util.*

@Component
class EncodingUtil {
    fun encode(toEncode: String): String {
        return Base64.getEncoder().encodeToString(toEncode.encodeToByteArray())
    }

    fun decode(toDecode: String): String {
        return String(Base64.getDecoder().decode(toDecode))
    }
}
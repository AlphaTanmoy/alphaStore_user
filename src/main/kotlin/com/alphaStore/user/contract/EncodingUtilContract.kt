package com.alphaStore.user.contract

interface EncodingUtilContract {

    fun encode(toEncode: String): String

    fun decode(toDecode: String): String
}
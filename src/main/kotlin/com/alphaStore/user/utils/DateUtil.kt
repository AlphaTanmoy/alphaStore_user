package com.alphaStore.user.utils

import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.*

@Component
object DateUtil {

    fun getZonedDateTimeFromStringUsingIsoFormatServerTimeZone(
        stringRep: String,
    ): Optional<ZonedDateTime> {
        val stringToParse = sanitizeDateTimeString(stringRep)
        return try {
            val dateTimeFormatter = DateTimeFormatter.ISO_DATE_TIME
            val localDateTime = LocalDateTime.parse(stringToParse, dateTimeFormatter)
            Optional.of(localDateTime.atZone(ZoneId.systemDefault()))
        } catch (ex: Exception) {
            ex.printStackTrace()
            Optional.empty()
        }
    }

    private fun sanitizeDateTimeString(stringToProcess: String, removeT: Boolean = false): String {
        var result = if (stringToProcess.contains(" ")) {
            stringToProcess.replace(" ", "+")
        } else {
            stringToProcess
        }
        if (removeT) {
            result = if (stringToProcess.contains("T")) {
                stringToProcess.replace("T", " ")
            } else {
                stringToProcess
            }
        }
        return result
    }
}
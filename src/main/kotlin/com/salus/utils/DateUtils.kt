package com.salus.com.salus.utils

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object DateUtils {
    private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

    fun localDateTimeToString(dateTime: LocalDateTime): String {
        return formatter.format(dateTime)
    }
}
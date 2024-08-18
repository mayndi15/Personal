package com.salus.exceptions

import org.springframework.http.HttpStatus
import java.util.Properties

enum class Message(
    val httpStatus: HttpStatus,
    val code: Long,
    val key: String
) {
    ID_NOT_FOUND(HttpStatus.BAD_REQUEST, 1L, "id.not.found");

    fun getReadableText(vararg args: Any): String {
        return getString(this.key, *args)
    }

    companion object I18N {
        private val messages: Properties = Properties()

        init {
            val classLoader = this::class.java.classLoader
            classLoader.getResourceAsStream("language_pt_BR.properties").use { inputStream ->
                messages.load(inputStream)

            }
        }

        fun getString(key: String, vararg args: Any): String {
            val message = messages.getProperty(key) ?: key
            return message.format(*args)
        }
    }
}
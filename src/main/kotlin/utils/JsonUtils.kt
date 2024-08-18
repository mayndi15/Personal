package com.salus.utils

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object JsonUtils {

    val gson = Gson()

    inline fun <reified T> mapper(json: String): T {
        return gson.fromJson(json, object : TypeToken<T>() {}.type)
    }
}
package com.caioluis.receitas.util

import android.content.Context

fun getJsonDataFromAsset(context: Context, fileName: String): String? {
    val jsonString: String
    return try {
        jsonString = context.assets.open(fileName)
            .bufferedReader()
            .use { it.readText() }
        jsonString
    } catch (ex: Exception) {
        null
    }
}
package com.caioluis.receitas.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IngredientsTypeConverter {
    @TypeConverter
    fun toJson(ingredients: List<String>): String? {
        val json = Gson().toJson(ingredients)
        return json
    }

    @TypeConverter
    fun fromJson(ingredientsJson: String): List<String> {
        val fromJson = Gson().fromJson<List<String>>(ingredientsJson, object : TypeToken<List<String>>() {}.type)
        return fromJson
    }
}

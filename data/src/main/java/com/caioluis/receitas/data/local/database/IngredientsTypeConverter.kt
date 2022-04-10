package com.caioluis.receitas.data.local.database

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class IngredientsTypeConverter {
    @TypeConverter
    fun toJson(ingredients: List<String>): String {
        return Gson().toJson(ingredients)
    }

    @TypeConverter
    fun fromJson(ingredientsJson: String): List<String> {
        return Gson().fromJson(ingredientsJson, object : TypeToken<List<String>>() {}.type)
    }
}

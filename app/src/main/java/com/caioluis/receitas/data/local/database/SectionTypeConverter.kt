package com.caioluis.receitas.data.local.database

import androidx.room.TypeConverter
import com.caioluis.receitas.data.model.Section
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class SectionTypeConverter {

    @TypeConverter
    fun toJson(sections: List<Section>): String {
        return Gson().toJson(sections)
    }

    @TypeConverter
    fun fromJson(sectionsJson: String): List<Section> {
        return Gson().fromJson(sectionsJson, object : TypeToken<List<Section>>() {}.type)
    }
}
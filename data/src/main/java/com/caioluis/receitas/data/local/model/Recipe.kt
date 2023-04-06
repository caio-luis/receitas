package com.caioluis.receitas.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.caioluis.receitas.data.local.database.DataBaseConstants.RECIPES_TABLE_NAME
import com.google.gson.annotations.SerializedName

@Entity(tableName = RECIPES_TABLE_NAME)
data class Recipe(

    @PrimaryKey(autoGenerate = false)
    @SerializedName("id")
    val id: String,

    @SerializedName("nome")
    val recipeName: String? = "",

    @SerializedName("secao")
    val sections: List<Section>? = listOf(),

    @SerializedName("ingredientes")
    val ingredients: List<String>? = listOf()
)

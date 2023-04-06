package com.caioluis.receitas.data.remote.model

import com.google.gson.annotations.SerializedName

data class RecipeRemote(
    @SerializedName("id")
    val id: String? = "",

    @SerializedName("nome")
    val recipeName: String? = "",

    @SerializedName("secao")
    val sections: List<SectionRemote>? = listOf(),

    @SerializedName("ingredientes")
    val ingredients: List<String>? = listOf(),
)

data class SectionRemote(
    @SerializedName("nome")
    val sectionName: String? = "",

    @SerializedName("conteudo")
    val content: List<String>? = listOf()
)

package com.caioluis.receitas.data.local.model

import com.google.gson.annotations.SerializedName

data class Section(
    @SerializedName("nome")
    val sectionName: String? = "",

    @SerializedName("conteudo")
    val content: List<String>? = listOf()
)

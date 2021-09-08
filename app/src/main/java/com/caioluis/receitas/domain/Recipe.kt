package com.caioluis.receitas.domain

import com.google.gson.annotations.SerializedName

data class Recipe(

    @SerializedName("_id")
    val id: Id,

    @SerializedName("nome")
    val recipeName: String,

    @SerializedName("secao")
    val sections: List<Section>
)
package com.caioluis.receitas.bridge.model

data class DomainRecipe(
    val id: String? = "",
    val recipeName: String? = "",
    val sections: List<DomainSection>? = listOf(),
    val ingredients: List<String>? = listOf()
)
package com.caioluis.receitas.domain.model

data class IngredientsToSearch(
    val ingredientsToSearch: MutableList<String> = mutableListOf(),
    var limitReached: Boolean = false
)
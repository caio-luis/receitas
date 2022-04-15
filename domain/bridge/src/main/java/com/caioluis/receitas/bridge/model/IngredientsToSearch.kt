package com.caioluis.receitas.bridge.model

data class IngredientsToSearch(
    val ingredientsToSearch: MutableList<String> = mutableListOf(),
    var limitReached: Boolean = false
)
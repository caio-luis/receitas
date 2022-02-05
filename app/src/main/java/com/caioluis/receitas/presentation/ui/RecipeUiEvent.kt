package com.caioluis.receitas.presentation.ui

sealed class RecipeUiEvent {
    data class Search(val ingredients: List<String>) : RecipeUiEvent()
    data class IngredientAdded(val ingredient: String) : RecipeUiEvent()
    data class IngredientRemoved(val ingredient: String) : RecipeUiEvent()
}

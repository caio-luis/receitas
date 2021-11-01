package com.caioluis.receitas.presentation.view

sealed class RecipeUiEvent {
    data class Search(val ingredients: List<String>) : RecipeUiEvent()
    data class IngredientClick(val ingredient: String) : RecipeUiEvent()
}

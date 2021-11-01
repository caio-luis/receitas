package com.caioluis.receitas.presentation.structure

sealed class RecipesCommand {
    data class AddIngredient(val ingredient: String) : RecipesCommand()
    data class SearchRecipes(val ingredients: List<String>) : RecipesCommand()
}
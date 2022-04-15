package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.bridge.base.Command

sealed class RecipesCommand : Command {
    data class AddIngredient(val ingredient: String) : RecipesCommand()
    data class RemoveIngredient(val ingredient: String) : RecipesCommand()
    data class SearchRecipes(val ingredients: List<String>) : RecipesCommand()
}
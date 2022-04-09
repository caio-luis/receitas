package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.structure.base.Effect
import com.caioluis.receitas.presentation.model.RecipeViewModel

sealed class RecipesEffect : Effect {
    object Loading : RecipesEffect()
    data class Error(val exception: Throwable) : RecipesEffect()
    data class AddIngredient(val ingredient: String) : RecipesEffect()
    data class RemoveIngredient(val ingredient: String) : RecipesEffect()
    data class ShowRecipes(val recipes: List<RecipeViewModel>) : RecipesEffect()
}

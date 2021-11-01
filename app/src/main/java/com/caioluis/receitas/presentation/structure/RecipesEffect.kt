package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.model.DomainRecipe

sealed class RecipesEffect {
    object Loading : RecipesEffect()
    data class Error(val exception: Throwable) : RecipesEffect()
    data class AddIngredient(val ingredient: String) : RecipesEffect()
    data class ShowRecipes(val recipes: List<DomainRecipe>) : RecipesEffect()
}

package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.presentation.model.RecipeViewModel

data class RecipesState(
        val loading: Boolean = false,
        val recipes: List<RecipeViewModel> = listOf(),
        val ingredientsToSearch: MutableList<String> = mutableListOf(),
        val error: Throwable? = null,
        val ingredientLimitReached: Boolean = false
)

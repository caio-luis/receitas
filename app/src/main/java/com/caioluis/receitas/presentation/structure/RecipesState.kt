package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.bridge.base.State
import com.caioluis.receitas.bridge.model.IngredientsToSearch
import com.caioluis.receitas.presentation.model.RecipeViewModel

data class RecipesState(
    val loading: Boolean = false,
    val recipes: List<RecipeViewModel> = listOf(),
    val ingredients: IngredientsToSearch = IngredientsToSearch(),
    val error: Throwable? = null
) : State

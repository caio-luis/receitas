package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.model.DomainRecipe

data class RecipesState(
        val loading: Boolean = false,
        val recipes: List<DomainRecipe> = listOf(),
        val ingredientsToSearch: MutableList<String> = mutableListOf(),
        val error: Throwable? = null
)

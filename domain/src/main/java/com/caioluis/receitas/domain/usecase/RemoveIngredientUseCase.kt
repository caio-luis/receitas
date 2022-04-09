package com.caioluis.receitas.domain.usecase

import com.caioluis.receitas.domain.model.IngredientsToSearch

interface RemoveIngredientUseCase : IngredientToSearchLimiter {
    operator fun invoke(
        ingredients: IngredientsToSearch,
        ingredient: String
    ): IngredientsToSearch
}
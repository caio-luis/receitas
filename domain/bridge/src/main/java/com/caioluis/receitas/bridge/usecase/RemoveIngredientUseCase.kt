package com.caioluis.receitas.bridge.usecase

import com.caioluis.receitas.bridge.model.IngredientsToSearch

interface RemoveIngredientUseCase : IngredientToSearchLimiter {
    operator fun invoke(
        ingredients: IngredientsToSearch,
        ingredient: String
    ): IngredientsToSearch
}
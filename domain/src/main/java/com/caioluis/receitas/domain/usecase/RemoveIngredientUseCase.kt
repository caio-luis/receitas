package com.caioluis.receitas.domain.usecase

import com.caioluis.receitas.domain.model.IngredientsToSearch

interface RemoveIngredientUseCase : IngredientToSearchLimiter {
    operator fun invoke(
        ingredients: IngredientsToSearch,
        ingredient: String
    ): IngredientsToSearch

    class Impl : RemoveIngredientUseCase {
        override fun invoke(
            ingredients: IngredientsToSearch,
            ingredient: String
        ): IngredientsToSearch {
            return ingredients.apply {
                if (ingredientsToSearch.size in 1..sizeLimit) {
                    ingredientsToSearch.remove(ingredient)
                    limitReached = false
                }
            }
        }
    }
}
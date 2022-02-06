package com.caioluis.receitas.domain.usecase

import com.caioluis.receitas.presentation.structure.IngredientsToSearch

interface AddIngredientsOnListUseCase : IngredientToSearchLimiter {
    operator fun invoke(
        ingredients: IngredientsToSearch,
        ingredient: String
    ): IngredientsToSearch

    class Impl : AddIngredientsOnListUseCase {
        override fun invoke(
            ingredients: IngredientsToSearch,
            ingredient: String
        ): IngredientsToSearch {
            return ingredients.apply {
                if (ingredientsToSearch.contains(ingredient).not()) {
                    limitReached = when (ingredientsToSearch.size) {
                        in 0 until sizeLimit -> {
                            ingredientsToSearch.add(ingredient)
                            ingredientsToSearch.size == sizeLimit
                        }
                        else -> true
                    }
                }
            }
        }
    }
}
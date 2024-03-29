package com.caioluis.receitas.usecase

import com.caioluis.receitas.domain.model.IngredientsToSearch
import com.caioluis.receitas.domain.usecase.RemoveIngredientUseCase

class RemoveIngredientUseCaseImpl : RemoveIngredientUseCase {
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
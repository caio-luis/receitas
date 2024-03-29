package com.caioluis.receitas.usecase

import com.caioluis.receitas.domain.model.IngredientsToSearch
import com.caioluis.receitas.domain.usecase.AddIngredientsOnListUseCase

class AddIngredientsOnListUseCaseImpl : AddIngredientsOnListUseCase {
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

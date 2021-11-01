package com.caioluis.receitas.domain.usecase

class AddIngredientsToListUseCaseImpl : AddIngredientsOnListUseCase {
    override fun invoke(
        ingredients: MutableList<String>,
        ingredient: String
    ): MutableList<String> {
        return ingredients.apply { add(ingredient) }
    }
}
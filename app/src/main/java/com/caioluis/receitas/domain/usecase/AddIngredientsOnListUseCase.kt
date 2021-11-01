package com.caioluis.receitas.domain.usecase

interface AddIngredientsOnListUseCase {
    operator fun invoke(
        ingredients: MutableList<String>,
        ingredient: String
    ): MutableList<String>
}
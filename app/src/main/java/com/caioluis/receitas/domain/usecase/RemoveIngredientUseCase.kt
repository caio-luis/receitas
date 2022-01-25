package com.caioluis.receitas.domain.usecase

interface RemoveIngredientUseCase {
    operator fun invoke(
        ingredients: MutableList<String>,
        ingredient: String
    ): MutableList<String>

    class Impl : RemoveIngredientUseCase {
        override fun invoke(
            ingredients: MutableList<String>,
            ingredient: String
        ): MutableList<String> {
            return ingredients.apply { remove(ingredient) }
        }
    }
}
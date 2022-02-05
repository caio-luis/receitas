package com.caioluis.receitas.domain.usecase

interface IngredientToSearchLimiter {
    val sizeLimit: Int
        get() = 5
}
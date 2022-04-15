package com.caioluis.receitas.bridge.usecase

interface IngredientToSearchLimiter {
    val sizeLimit: Int
        get() = 5
}
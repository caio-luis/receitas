package com.caioluis.receitas.domain.usecase

import com.caioluis.receitas.presentation.structure.RecipesEffect
import io.reactivex.Observable

interface GetRecipesByIngredientsUseCase {
    operator fun invoke(ingredients: List<String>): Observable<RecipesEffect>
}
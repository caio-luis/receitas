package com.caioluis.receitas.bridge.usecase

import com.caioluis.receitas.bridge.base.Effect
import io.reactivex.Observable

interface GetRecipesByIngredientsUseCase {
    operator fun invoke(ingredients: List<String>): Observable<Effect>
}
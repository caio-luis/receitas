package com.caioluis.receitas.domain.usecase

import com.caioluis.receitas.domain.structure.base.Effect
import io.reactivex.Observable

interface GetRecipesByIngredientsUseCase {
    operator fun invoke(ingredients: List<String>): Observable<Effect>
}
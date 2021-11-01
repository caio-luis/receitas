package com.caioluis.receitas.domain.usecase

import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import com.caioluis.receitas.presentation.structure.RecipesEffect
import io.reactivex.Observable

class FakeUseCase(private val effect: RecipesEffect) : GetRecipesByIngredientsUseCase {
    override fun invoke(ingredients: List<String>): Observable<RecipesEffect> {
        return Observable.just(effect)
    }
}
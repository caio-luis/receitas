package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.presentation.base.Interactor
import io.reactivex.Observable

interface RecipesInteractor : Interactor<RecipesState, RecipesCommand, RecipesEffect> {
    override fun invoke(state: RecipesState, command: RecipesCommand): Observable<RecipesEffect>
}
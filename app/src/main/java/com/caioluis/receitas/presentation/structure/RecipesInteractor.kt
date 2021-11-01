package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import com.caioluis.receitas.presentation.base.Interactor
import io.reactivex.Observable

class RecipesInteractor(private val getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase)
    : Interactor<RecipesState, RecipesCommand, RecipesEffect> {

    override fun invoke(state: RecipesState, command: RecipesCommand): Observable<RecipesEffect> {
        return when (command) {
            is RecipesCommand.AddIngredient ->
                Observable.just(RecipesEffect.AddIngredient(command.ingredient))

            is RecipesCommand.SearchRecipes -> getRecipesByIngredientsUseCase(command.ingredients)
        }
    }
}
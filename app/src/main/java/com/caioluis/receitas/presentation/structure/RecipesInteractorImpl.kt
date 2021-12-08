package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import io.reactivex.Observable

class RecipesInteractorImpl(
    private val getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase
) : RecipesInteractor {

    override fun invoke(state: RecipesState, command: RecipesCommand): Observable<RecipesEffect> {
        return when (command) {
            is RecipesCommand.AddIngredient ->
                Observable.just(RecipesEffect.AddIngredient(command.ingredient))

            is RecipesCommand.SearchRecipes -> getRecipesByIngredientsUseCase(command.ingredients)
        }
    }
}
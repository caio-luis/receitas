package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.app.interactor.RecipesInteractor
import com.caioluis.receitas.domain.base.Command
import com.caioluis.receitas.domain.base.Effect
import com.caioluis.receitas.domain.base.State
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import io.reactivex.Observable

class RecipesInteractorImpl(
    private val getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase
) : RecipesInteractor {

    override fun invoke(state: State, command: Command): Observable<Effect> {
        return when (command) {
            is RecipesCommand.AddIngredient -> Observable.just(RecipesEffect.AddIngredient(command.ingredient))
            is RecipesCommand.RemoveIngredient -> Observable.just(
                RecipesEffect.RemoveIngredient(
                    command.ingredient
                )
            )
            else -> {
                command as RecipesCommand.SearchRecipes
                getRecipesByIngredientsUseCase(command.ingredients)
            }
        }
    }
}

package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.usecase.AddIngredientsOnListUseCase
import com.caioluis.receitas.domain.usecase.RemoveIngredientUseCase

class RecipesReducerImpl(
    private val addIngredientsOnListUseCase: AddIngredientsOnListUseCase,
    private val removeIngredientUseCase: RemoveIngredientUseCase
) : RecipesReducer {
    override fun invoke(state: RecipesState, effect: RecipesEffect): RecipesState =
        when (effect) {
            is RecipesEffect.ShowRecipes -> state.copy(
                loading = false,
                recipes = effect.recipes,
                ingredients = state.ingredients,
                error = null
            )

            is RecipesEffect.AddIngredient -> state.copy(
                loading = false,
                recipes = state.recipes,
                ingredients = addIngredientsOnListUseCase(
                    state.ingredients,
                    effect.ingredient
                ),
                error = null
            )
            is RecipesEffect.Error -> state.copy(
                loading = false,
                recipes = state.recipes,
                ingredients = state.ingredients,
                error = effect.exception
            )

            RecipesEffect.Loading -> state.copy(
                loading = true,
                recipes = state.recipes,
                ingredients = state.ingredients,
                error = null
            )
            is RecipesEffect.RemoveIngredient -> state.copy(
                loading = false,
                recipes = state.recipes,
                ingredients = removeIngredientUseCase(
                    state.ingredients,
                    effect.ingredient
                ),
                error = null
            )
        }
}
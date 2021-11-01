package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.presentation.base.Reducer
import com.caioluis.receitas.domain.usecase.AddIngredientsOnListUseCase

class RecipesReducer(
    private val addIngredientsOnListUseCase: AddIngredientsOnListUseCase
) : Reducer<RecipesState, RecipesEffect> {
    override fun invoke(state: RecipesState, effect: RecipesEffect): RecipesState =
        when (effect) {
            is RecipesEffect.ShowRecipes -> state.copy(
                loading = false,
                recipes = effect.recipes,
                error = null
            )

            is RecipesEffect.AddIngredient -> state.copy(
                loading = false,
                recipes = state.recipes,
                ingredientsToSearch = addIngredientsOnListUseCase(
                    state.ingredientsToSearch,
                    effect.ingredient
                ),
                error = null
            )
            is RecipesEffect.Error -> state.copy(
                loading = false,
                recipes = state.recipes,
                error = effect.exception
            )

            RecipesEffect.Loading -> state.copy(
                loading = true,
                recipes = state.recipes,
                error = null
            )
        }
}
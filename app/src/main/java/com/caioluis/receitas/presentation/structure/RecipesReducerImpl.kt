package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.usecase.AddIngredientsOnListUseCase

class RecipesReducerImpl(
    private val addIngredientsOnListUseCase: AddIngredientsOnListUseCase
) : RecipesReducer {
    override fun invoke(state: RecipesState, effect: RecipesEffect): RecipesState =
        when (effect) {
            is RecipesEffect.ShowRecipes -> state.copy(
                loading = false,
                recipes = effect.recipes,
                ingredientsToSearch = state.ingredientsToSearch,
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
                ingredientsToSearch = state.ingredientsToSearch,
                error = effect.exception
            )

            RecipesEffect.Loading -> state.copy(
                loading = true,
                recipes = state.recipes,
                ingredientsToSearch = state.ingredientsToSearch,
                error = null
            )
        }
}
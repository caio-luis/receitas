package com.caioluis.receitas.presentation.view

import com.caioluis.receitas.presentation.structure.RecipesCommand

class RecipesUiEventTransformer : (RecipeUiEvent) -> RecipesCommand {
    override fun invoke(uiEvent: RecipeUiEvent): RecipesCommand =
        when (uiEvent) {
            is RecipeUiEvent.IngredientClick -> RecipesCommand.AddIngredient(uiEvent.ingredient)
            is RecipeUiEvent.Search -> RecipesCommand.SearchRecipes(uiEvent.ingredients)
        }
}

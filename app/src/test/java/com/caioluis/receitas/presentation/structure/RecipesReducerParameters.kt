package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.Fixtures
import com.caioluis.receitas.domain.model.IngredientsToSearch
import com.caioluis.receitas.presentation.mapper.toViewModel
import com.caioluis.receitas.presentation.structure.RecipesReducerTest.Parameter
import com.caioluis.receitas.toDomain

object RecipesReducerParameters {

    private val errorMock = Throwable("test error")
    private val recipesMock = Fixtures.getRecipesMockFromJsonResource().map { it.toDomain() }

    val showRecipesCase = Parameter(
        effect = RecipesEffect.ShowRecipes(recipes = listOf()),
        finalState = RecipesState(
            loading = false,
            recipes = listOf(),
            ingredients = IngredientsToSearch(),
            error = null
        )
    )

    val addOneIngredientCase = Parameter(
        effect = RecipesEffect.AddIngredient(ingredient = "teste"),
        initialState = RecipesState(
            loading = false,
            recipes = listOf(),
            ingredients = IngredientsToSearch(),
            error = null
        ),
        finalState = RecipesState(
            loading = false,
            recipes = listOf(),
            ingredients = IngredientsToSearch(mutableListOf("teste")),
            error = null
        )
    )

    val removeOneIngredientFromListCase = Parameter(
        effect = RecipesEffect.RemoveIngredient(ingredient = "teste"),
        finalState = RecipesState(
            loading = false,
            recipes = listOf(),
            ingredients = IngredientsToSearch(),
            error = null
        )
    )
    val loadingRecipesCase = Parameter(
        effect = RecipesEffect.Loading,
        finalState = RecipesState(
            loading = true,
            recipes = listOf(),
            ingredients = IngredientsToSearch(),
            error = null
        )
    )
    val errorCase = Parameter(
        effect = RecipesEffect.Error(errorMock),
        finalState = RecipesState(
            loading = false,
            recipes = listOf(),
            ingredients = IngredientsToSearch(),
            error = errorMock
        )
    )
    val showRecipesWithEmptyResultCase = Parameter(
        effect = RecipesEffect.ShowRecipes(listOf()),
        initialState = RecipesState(
            loading = false,
            recipes = recipesMock.map { it.toViewModel() },
            ingredients = IngredientsToSearch(),
            error = null
        ),
        finalState = RecipesState(
            loading = false,
            recipes = listOf(),
            ingredients = IngredientsToSearch(),
            error = null
        )
    )
}
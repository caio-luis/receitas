package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

class RecipesInteractorTest {

    private lateinit var interactor: RecipesInteractorImpl

    private lateinit var getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase

    @Before
    fun setup() {
        getRecipesByIngredientsUseCase = mockk(relaxed = true)
        interactor = RecipesInteractorImpl(getRecipesByIngredientsUseCase)
    every {
        getRecipesByIngredientsUseCase.invoke(ingredients = listOf())
    } returns Observable.just(
        RecipesEffect.ShowRecipes(emptyList())
    )
    }

    @Test
    fun `when invoke interactor with SearchRecipes command should return ShowRecipes effect`() {
        val response = interactor.invoke(
            RecipesState(),
            RecipesCommand.SearchRecipes(listOf())
        )

        response.test().assertResult(RecipesEffect.ShowRecipes(emptyList()))
    }

    @Test
    fun `when invoke interactor with AddIngredient command should return AddIngredient effect`() {
        val response = interactor.invoke(
            RecipesState(),
            RecipesCommand.AddIngredient("")
        )

        response.test().assertResult(RecipesEffect.AddIngredient(""))
    }
}

package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.bridge.usecase.GetRecipesByIngredientsUseCase
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RecipesInteractorTest {

    private lateinit var interactor: RecipesInteractorImpl

    @Mock
    private lateinit var getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase

    @Before
    fun setup() {
        getRecipesByIngredientsUseCase =
            mock(GetRecipesByIngredientsUseCase::class.java)
        interactor = RecipesInteractorImpl(getRecipesByIngredientsUseCase)

        `when`(getRecipesByIngredientsUseCase.invoke(ingredients = listOf()))
            .thenReturn(
                Observable.just(
                    RecipesEffect.ShowRecipes(emptyList())
                )
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

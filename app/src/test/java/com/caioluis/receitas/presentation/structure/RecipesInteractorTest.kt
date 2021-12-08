package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.usecase.FakeUseCase
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@RunWith(MockitoJUnitRunner::class)
class RecipesInteractorTest {

    private lateinit var interactor: RecipesInteractorImpl

    @Mock
    private lateinit var getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase

    @Before
    fun setup() {
        getRecipesByIngredientsUseCase = mock(GetRecipesByIngredientsUseCase::class.java)
        interactor = RecipesInteractorImpl(getRecipesByIngredientsUseCase)
    }

    @Test
    fun `when invoke interactor with SearchRecipes command should return ShowRecipes effect`() {
        val getRecipesByIngredientsUseCaseTest = FakeUseCase(RecipesEffect.ShowRecipes(emptyList()))

        val interactor = RecipesInteractorImpl(getRecipesByIngredientsUseCaseTest)

        val response = interactor.invoke(
            RecipesState(false, listOf(), mutableListOf()),
            RecipesCommand.SearchRecipes(listOf())
        )

        response.test().assertResult(RecipesEffect.ShowRecipes(emptyList()))
    }

    @Test
    fun `when invoke interactor with AddIngredient command should return AddIngredient effect`() {
        val getRecipesByIngredientsUseCaseTest = FakeUseCase(RecipesEffect.AddIngredient(""))
        val interactor = RecipesInteractorImpl(getRecipesByIngredientsUseCaseTest)

        val response = interactor.invoke(
            RecipesState(false, listOf(), mutableListOf()),
            RecipesCommand.AddIngredient("")
        )

        response.test().assertResult(RecipesEffect.AddIngredient(""))
    }

    @Test
    fun `when invoke interactor with SearchRecipes command should return ShowRecipes effect (mockito)`() {

        // Exemplo com Mockito

        whenever(getRecipesByIngredientsUseCase.invoke(ingredients = listOf()))
            .thenReturn(
                Observable.just(
                    RecipesEffect.ShowRecipes(emptyList())
                )
            )

        val response = interactor.invoke(
            RecipesState(false, listOf(), mutableListOf()),
            RecipesCommand.SearchRecipes(listOf())
        )

        response.test().assertResult(RecipesEffect.ShowRecipes(emptyList()))
    }
}

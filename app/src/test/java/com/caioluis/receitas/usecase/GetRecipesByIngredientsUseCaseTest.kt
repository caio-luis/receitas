package com.caioluis.receitas.usecase

import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import com.caioluis.receitas.Fixtures
import com.caioluis.receitas.data.RecipesDataSource
import com.caioluis.receitas.presentation.mapper.toViewModel
import com.caioluis.receitas.presentation.structure.RecipesEffect
import com.caioluis.receitas.toDomain
import com.caioluis.receitas.util.TrampolineSchedulerProvider
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class GetRecipesByIngredientsUseCaseTest {

    private lateinit var getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase
    private lateinit var scheduler: TrampolineSchedulerProvider

    private lateinit var recipesDataSourceMock: RecipesDataSource

    @Before
    fun setUp() {
        recipesDataSourceMock = mockk(relaxed = true)
        scheduler = TrampolineSchedulerProvider()
        getRecipesByIngredientsUseCase =
            GetRecipesByIngredientsUseCaseImpl(recipesDataSourceMock, scheduler)
    }

    @Test
    fun `should return Loading and ShowRecipes effect in sequence`() {
        every{
            recipesDataSourceMock.getRecipes(any())
        } returns Observable.just(listOf())

        val response = getRecipesByIngredientsUseCase.invoke(listOf())

        response.test()
            .assertResult(RecipesEffect.Loading, RecipesEffect.ShowRecipes(listOf()))
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun `should return Loading and ShowRecipes effect in sequence with content`() {
        val recipesMock = Fixtures.getRecipesMockFromJsonResource().map { it.toDomain() }

        every {
            recipesDataSourceMock.getRecipes(any())
        } returns Observable.just(recipesMock)

        val response = getRecipesByIngredientsUseCase.invoke(listOf())

        response
            .test()
            .assertResult(
                RecipesEffect.Loading,
                RecipesEffect.ShowRecipes(recipesMock.map { it.toViewModel() })
            )
            .assertComplete()
            .assertNoErrors()
    }
}
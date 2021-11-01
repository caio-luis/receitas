package com.caioluis.receitas.domain.usecase

import com.caioluis.receitas.Fixtures
import com.caioluis.receitas.data.local.RecipesDataSource
import com.caioluis.receitas.presentation.structure.RecipesEffect
import com.caioluis.receitas.toDomain
import com.caioluis.receitas.util.TrampolineSchedulerProvider
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyList
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@RunWith(MockitoJUnitRunner::class)
class GetRecipesByIngredientsUseCaseImplTest {

    private lateinit var getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase
    private lateinit var scheduler: TrampolineSchedulerProvider

    @Mock
    private lateinit var recipesDataSourceMock: RecipesDataSource

    @Before
    fun setUp() {
        recipesDataSourceMock = mock(RecipesDataSource::class.java)
        scheduler = TrampolineSchedulerProvider()
        getRecipesByIngredientsUseCase =
            GetRecipesByIngredientsUseCaseImpl(recipesDataSourceMock, scheduler)
    }

    @Test
    fun `should return Loading and ShowRecipes effect in sequence`() {
        whenever(recipesDataSourceMock.getRecipes(anyList()))
            .thenReturn(Observable.just(listOf()))

        val response = getRecipesByIngredientsUseCase.invoke(listOf())

        response.test()
            .assertResult(RecipesEffect.Loading, RecipesEffect.ShowRecipes(listOf()))
            .assertComplete()
            .assertNoErrors()
    }

    @Test
    fun `sshould return Loading and ShowRecipes effect in sequence with content`() {
        val recipesMock = Fixtures.getRecipesMockFromJsonResource().map { it.toDomain() }

        whenever(recipesDataSourceMock.getRecipes(anyList()))
            .thenReturn(Observable.just(recipesMock))

        val response = getRecipesByIngredientsUseCase.invoke(listOf())

        response
            .test()
            .assertResult(RecipesEffect.Loading, RecipesEffect.ShowRecipes(recipesMock))
            .assertComplete()
            .assertNoErrors()
    }
}
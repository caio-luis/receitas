package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.data.RecipesDataSource
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import com.caioluis.receitas.presentation.view.RecipeUiEvent
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito.mock

class RecipesPresenterTest {

    @Mock private lateinit var getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase
    @Mock private lateinit var recipesDataSource: RecipesDataSource
    @Mock private lateinit var interactor: RecipesInteractor
    @Mock private lateinit var reducer: RecipesReducer

    private lateinit var presenter: RecipesPresenter

    @Before
    fun setUp() {
        recipesDataSource = mock(RecipesDataSource::class.java)
        getRecipesByIngredientsUseCase = mock(GetRecipesByIngredientsUseCase::class.java)
        interactor = mock(RecipesInteractor::class.java)
        reducer = mock(RecipesReducer::class.java)
        presenter = RecipesPresenter(interactor, reducer)
    }

    @Test
    fun `assert that search event dispatch correctly`() {

        presenter.dispatchCommand(RecipeUiEvent.Search(listOf()))

        presenter.stateSubject.test()
            .assertSubscribed()
            .assertValue(
                RecipesState(loading = false)
            ).assertNoErrors()
    }
}
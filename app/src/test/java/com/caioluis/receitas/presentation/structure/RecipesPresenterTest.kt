package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.app.interactor.RecipesInteractor
import com.caioluis.receitas.domain.app.reducer.RecipesReducer
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import com.caioluis.receitas.data.RecipesDataSource
import com.caioluis.receitas.presentation.ui.RecipeUiEvent
import com.caioluis.receitas.util.TrampolineSchedulerProvider
import io.mockk.mockk
import org.junit.Before
import org.junit.Test

class RecipesPresenterTest {

    private lateinit var getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase

    private lateinit var recipesDataSource: RecipesDataSource

    private lateinit var interactor: RecipesInteractor

    private lateinit var reducer: RecipesReducer

    private lateinit var schedulerProvider: TrampolineSchedulerProvider
    private lateinit var presenter: RecipesPresenter

    @Before
    fun setUp() {
        getRecipesByIngredientsUseCase = mockk(relaxed = true)
        recipesDataSource = mockk(relaxed = true)
        schedulerProvider = TrampolineSchedulerProvider()
        interactor = mockk(relaxed = true)
        reducer = mockk(relaxed = true)
        presenter = RecipesPresenter(
            recipesInteractor = interactor,
            recipesReducer = reducer,
            schedulerProvider = schedulerProvider
        )
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
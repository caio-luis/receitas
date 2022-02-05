package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.data.RecipesDataSource
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import com.caioluis.receitas.presentation.ui.RecipeUiEvent
import com.caioluis.receitas.util.TrampolineSchedulerProvider
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock

class RecipesPresenterTest {

    @Mock private lateinit var getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase
    @Mock private lateinit var recipesDataSource: RecipesDataSource
    @Mock private lateinit var interactor: RecipesInteractor
    @Mock private lateinit var reducer: RecipesReducer

    private lateinit var schedulerProvider: TrampolineSchedulerProvider
    private lateinit var presenter: RecipesPresenter

    @Before
    fun setUp() {
        recipesDataSource = mock(RecipesDataSource::class.java)
        getRecipesByIngredientsUseCase = mock(GetRecipesByIngredientsUseCase::class.java)
        interactor = mock(RecipesInteractor::class.java)
        reducer = mock(RecipesReducer::class.java)
        schedulerProvider = TrampolineSchedulerProvider()
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
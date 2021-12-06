package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.data.RecipesDataSource
import com.caioluis.receitas.domain.usecase.AddIngredientsToListUseCaseImpl
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCaseImpl
import com.caioluis.receitas.presentation.view.RecipeUiEvent
import com.caioluis.receitas.util.TrampolineSchedulerProvider
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when` as whenever

class RecipesPresenterTest {

    private lateinit var presenter: RecipesPresenter
    private lateinit var interactor: RecipesInteractor
    private lateinit var reducer: RecipesReducer
    private val ingredientsOnListUseCase = AddIngredientsToListUseCaseImpl()
    private lateinit var getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase

    @Mock
    private lateinit var recipesDataSource: RecipesDataSource

    @Before
    fun setUp() {
        recipesDataSource = mock(RecipesDataSource::class.java)

        whenever(recipesDataSource.getRecipes(listOf())).thenReturn(Observable.just(listOf()))

        getRecipesByIngredientsUseCase =
            GetRecipesByIngredientsUseCaseImpl(recipesDataSource, TrampolineSchedulerProvider())

        interactor = RecipesInteractor(getRecipesByIngredientsUseCase)
        reducer = RecipesReducer(ingredientsOnListUseCase)

        presenter = RecipesPresenter(interactor, reducer)
    }

    @Test
    fun `assert that search event dispatch correctly`() {

        presenter.dispatchCommand(RecipeUiEvent.Search(listOf()))

        presenter.stateSubject.test()
            .assertSubscribed()
            .assertValue(
                RecipesState(loading = false)
            )
            .assertNoErrors()
    }
}
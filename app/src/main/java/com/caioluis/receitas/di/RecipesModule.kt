package com.caioluis.receitas.di

import com.caioluis.receitas.bridge.app.interactor.RecipesInteractor
import com.caioluis.receitas.bridge.app.reducer.RecipesReducer
import com.caioluis.receitas.bridge.usecase.AddIngredientsOnListUseCase
import com.caioluis.receitas.bridge.usecase.GetRecipesByIngredientsUseCase
import com.caioluis.receitas.bridge.usecase.RemoveIngredientUseCase
import com.caioluis.receitas.data.RecipesDataSource
import com.caioluis.receitas.data.local.mapper.IngredientsSearchSqlQueryMapper
import com.caioluis.receitas.data.local.mapper.RecipesLocalMapper
import com.caioluis.receitas.presentation.structure.RecipesInteractorImpl
import com.caioluis.receitas.presentation.structure.RecipesPresenter
import com.caioluis.receitas.presentation.structure.RecipesReducerImpl
import com.caioluis.receitas.usecase.AddIngredientsOnListUseCaseImpl
import com.caioluis.receitas.usecase.GetRecipesByIngredientsUseCaseImpl
import com.caioluis.receitas.usecase.RemoveIngredientUseCaseImpl
import com.caioluis.receitas.util.BaseSchedulerProvider
import com.caioluis.receitas.util.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
object RecipesModule {

    @[Provides JvmStatic]
    fun provideScheduler(): BaseSchedulerProvider = SchedulerProvider()

    // Use Cases
    @[Provides JvmStatic]
    fun provideAddIngredientsOnListUseCase(): AddIngredientsOnListUseCase =
        AddIngredientsOnListUseCaseImpl()

    @[Provides JvmStatic]
    fun provideRemoveIngredientUseCase(): RemoveIngredientUseCase =
        RemoveIngredientUseCaseImpl()

    @[Provides JvmStatic]
    fun provideGetRecipesByIngredientsUseCase(
        dataSource: RecipesDataSource,
        scheduler: BaseSchedulerProvider
    ): GetRecipesByIngredientsUseCase = GetRecipesByIngredientsUseCaseImpl(dataSource, scheduler)

    // Mappers
    @[Provides JvmStatic]
    fun provideIngredientsSearchSqlQueryMapper(): IngredientsSearchSqlQueryMapper =
        IngredientsSearchSqlQueryMapper.Impl()

    @[Provides JvmStatic]
    fun provideRecipesLocalMapper(): RecipesLocalMapper = RecipesLocalMapper.Impl()

    // Presentation
    @[Provides JvmStatic]
    fun provideRecipesPresenter(
        recipesInteractor: RecipesInteractor,
        recipesReducer: RecipesReducer,
        scheduler: BaseSchedulerProvider
    ): RecipesPresenter = RecipesPresenter(
        recipesInteractor = recipesInteractor,
        recipesReducer = recipesReducer,
        schedulerProvider = scheduler
    )

    @[Provides JvmStatic]
    fun provideRecipesReducer(
        addIngredientsOnListUseCase: AddIngredientsOnListUseCase,
        removeIngredientUseCase: RemoveIngredientUseCase
    ): RecipesReducer = RecipesReducerImpl(addIngredientsOnListUseCase, removeIngredientUseCase)

    @[Provides JvmStatic]
    fun provideRecipesInteractor(
        getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase
    ): RecipesInteractor = RecipesInteractorImpl(getRecipesByIngredientsUseCase)
}

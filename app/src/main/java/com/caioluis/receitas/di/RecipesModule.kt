package com.caioluis.receitas.di

import com.caioluis.receitas.data.RecipesDataSource
import com.caioluis.receitas.domain.app.interactor.RecipesInteractor
import com.caioluis.receitas.domain.app.reducer.RecipesReducer
import com.caioluis.receitas.domain.usecase.AddIngredientsOnListUseCase
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import com.caioluis.receitas.domain.usecase.RemoveIngredientUseCase
import com.caioluis.receitas.presentation.mapper.RecipeDetailsMapper
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
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RecipesModule {

    @[Provides Singleton]
    fun provideScheduler(): BaseSchedulerProvider = SchedulerProvider()

    // Use Cases
    @[Provides Singleton]
    fun provideAddIngredientsOnListUseCase(): AddIngredientsOnListUseCase =
        AddIngredientsOnListUseCaseImpl()

    @[Provides Singleton]
    fun provideRemoveIngredientUseCase(): RemoveIngredientUseCase =
        RemoveIngredientUseCaseImpl()

    @[Provides Singleton]
    fun provideGetRecipesByIngredientsUseCase(
        dataSource: RecipesDataSource,
        scheduler: BaseSchedulerProvider
    ): GetRecipesByIngredientsUseCase = GetRecipesByIngredientsUseCaseImpl(dataSource, scheduler)

    // Mappers

    @[Provides Singleton]
    fun provideRecipeDetailsMapper(): RecipeDetailsMapper =
        RecipeDetailsMapper.RecipeDetailsMapperImpl()

    // Presentation
    @[Provides Singleton]
    fun provideRecipesPresenter(
        recipesInteractor: RecipesInteractor,
        recipesReducer: RecipesReducer,
        scheduler: BaseSchedulerProvider
    ): RecipesPresenter = RecipesPresenter(
        recipesInteractor = recipesInteractor,
        recipesReducer = recipesReducer,
        schedulerProvider = scheduler
    )

    @[Provides Singleton]
    fun provideRecipesReducer(
        addIngredientsOnListUseCase: AddIngredientsOnListUseCase,
        removeIngredientUseCase: RemoveIngredientUseCase
    ): RecipesReducer = RecipesReducerImpl(addIngredientsOnListUseCase, removeIngredientUseCase)

    @[Provides Singleton]
    fun provideRecipesInteractor(
        getRecipesByIngredientsUseCase: GetRecipesByIngredientsUseCase
    ): RecipesInteractor = RecipesInteractorImpl(getRecipesByIngredientsUseCase)
}

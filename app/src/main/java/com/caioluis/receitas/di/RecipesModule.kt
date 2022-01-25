package com.caioluis.receitas.di

import android.content.Context
import com.caioluis.receitas.data.RecipesDataSource
import com.caioluis.receitas.data.local.database.RecipesDao
import com.caioluis.receitas.data.local.database.RecipesDataBase
import com.caioluis.receitas.data.local.mapper.IngredientsSearchSqlQueryMapper
import com.caioluis.receitas.data.local.mapper.RecipesLocalMapper
import com.caioluis.receitas.domain.usecase.AddIngredientsOnListUseCase
import com.caioluis.receitas.domain.usecase.AddIngredientsToListUseCaseImpl
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCaseImpl
import com.caioluis.receitas.domain.usecase.RemoveIngredientUseCase
import com.caioluis.receitas.presentation.structure.RecipesInteractor
import com.caioluis.receitas.presentation.structure.RecipesInteractorImpl
import com.caioluis.receitas.presentation.structure.RecipesPresenter
import com.caioluis.receitas.presentation.structure.RecipesReducer
import com.caioluis.receitas.presentation.structure.RecipesReducerImpl
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
        AddIngredientsToListUseCaseImpl()

    @[Provides JvmStatic]
    fun provideRemoveIngredientUseCase(): RemoveIngredientUseCase =
        RemoveIngredientUseCase.Impl()

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

    // Data
    @[Provides JvmStatic]
    fun provideRecipesDataSource(
        dao: RecipesDao,
        mapper: RecipesLocalMapper,
        queryMapperIngredientsSearch: IngredientsSearchSqlQueryMapper
    ): RecipesDataSource = RecipesDataSource.LocalImpl(dao, mapper, queryMapperIngredientsSearch)

    @[Provides JvmStatic]
    fun provideRecipesDao(context: Context): RecipesDao =
        RecipesDataBase.getInstance(context).recipesDao()

    // Presentation
    @[Provides JvmStatic]
    fun provideRecipesPresenter(
        recipesInteractor: RecipesInteractor,
        recipesReducer: RecipesReducer
    ): RecipesPresenter = RecipesPresenter(recipesInteractor, recipesReducer)

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

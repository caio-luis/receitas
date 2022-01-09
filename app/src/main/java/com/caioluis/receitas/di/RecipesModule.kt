package com.caioluis.receitas.di

import android.content.Context
import com.caioluis.receitas.data.RecipesDataSource
import com.caioluis.receitas.data.local.database.RecipesDao
import com.caioluis.receitas.data.local.database.RecipesDataBase
import com.caioluis.receitas.data.local.mapper.IngredientsSearchSqlQueryMapper
import com.caioluis.receitas.data.local.mapper.RecipesLocalMapper
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCase
import com.caioluis.receitas.domain.usecase.GetRecipesByIngredientsUseCaseImpl
import com.caioluis.receitas.util.BaseSchedulerProvider
import dagger.Module
import dagger.Provides

@Module
object RecipesModule {

    @Provides
    fun provideGetRecipesByIngredientsUseCase(
        dataSource: RecipesDataSource,
        scheduler: BaseSchedulerProvider
    ): GetRecipesByIngredientsUseCase = GetRecipesByIngredientsUseCaseImpl(dataSource, scheduler)

    @Provides
    fun provideRecipesDataSource(
        dao: RecipesDao,
        mapper: RecipesLocalMapper,
        queryMapperIngredientsSearch: IngredientsSearchSqlQueryMapper
    ): RecipesDataSource = RecipesDataSource.LocalImpl(dao, mapper, queryMapperIngredientsSearch)

    @Provides
    fun provideRecipesLocalMapper(): RecipesLocalMapper = RecipesLocalMapper.Impl()

    @Provides
    fun provideRecipesDao(
        context: Context
    ): RecipesDao = RecipesDataBase.getInstance(context).recipesDao()

    @Provides
    fun provideIngredientsSearchSqlQueryMapper(): IngredientsSearchSqlQueryMapper =
        IngredientsSearchSqlQueryMapper.Impl()
}
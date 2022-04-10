package com.caioluis.receitas.data.di

import android.content.Context
import com.caioluis.receitas.data.RecipesDataSource
import com.caioluis.receitas.data.local.database.RecipesDao
import com.caioluis.receitas.data.local.database.RecipesDataBase
import com.caioluis.receitas.data.local.mapper.IngredientsSearchSqlQueryMapper
import com.caioluis.receitas.data.local.mapper.RecipesLocalMapper
import dagger.Module
import dagger.Provides

@Module
object DataModule {

    @[Provides JvmStatic]
    fun provideRecipesDataSource(
        dao: RecipesDao,
        mapper: RecipesLocalMapper,
        queryMapperIngredientsSearch: IngredientsSearchSqlQueryMapper
    ): RecipesDataSource = RecipesDataSource.LocalImpl(dao, mapper, queryMapperIngredientsSearch)

    @[Provides JvmStatic]
    fun provideRecipesDao(context: Context): RecipesDao =
        RecipesDataBase.getInstance(context).recipesDao()
}

package com.caioluis.receitas.data.di

import android.content.Context
import com.caioluis.receitas.data.RecipesDataSource
import com.caioluis.receitas.data.RecipesDataSourceImpl
import com.caioluis.receitas.data.local.LocalImpl
import com.caioluis.receitas.data.local.LocalSource
import com.caioluis.receitas.data.local.database.RecipesDao
import com.caioluis.receitas.data.local.database.RecipesDataBase
import com.caioluis.receitas.data.local.mapper.IngredientsSearchSqlQueryMapper
import com.caioluis.receitas.data.local.mapper.RecipesLocalMapper
import com.caioluis.receitas.data.remote.RecipesService
import com.caioluis.receitas.data.remote.RemoteImpl
import com.caioluis.receitas.data.remote.RemoteSource
import com.caioluis.receitas.data.remote.mapper.RecipesRemoteMapper
import com.caioluis.receitas.data.remote.mapper.RecipesRemoteMapperImpl
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
object DataModule {

    @[Provides JvmStatic]
    fun provideRecipesDataSource(
        localImpl: LocalSource,
        remoteImpl: RemoteSource,
    ): RecipesDataSource = RecipesDataSourceImpl(localImpl, remoteImpl)

    @[Provides JvmStatic]
    fun provideRecipesDao(context: Context): RecipesDao =
        RecipesDataBase.getInstance(context).recipesDao()

    @[Provides JvmStatic]
    fun provideRecipesLocalMapper(): RecipesLocalMapper =
        RecipesLocalMapper.Impl()

    @[Provides JvmStatic]
    fun provideIngredientsSearchSqlQueryMapper(): IngredientsSearchSqlQueryMapper =
        IngredientsSearchSqlQueryMapper.Impl()

    @[Provides JvmStatic]
    fun provideRecipesRemoteMapper(): RecipesRemoteMapper =
        RecipesRemoteMapperImpl()

    @[Provides JvmStatic]
    fun provideLocalImpl(
        dao: RecipesDao,
        mapper: RecipesLocalMapper,
        queryMapperIngredientsSearch: IngredientsSearchSqlQueryMapper
    ): LocalSource =
        LocalImpl(dao, mapper, queryMapperIngredientsSearch)

    @[Provides JvmStatic]
    fun provideRemoteImpl(
        service: RecipesService,
        remoteMapperImpl: RecipesRemoteMapper
    ): RemoteSource =
        RemoteImpl(service, remoteMapperImpl)

    @[Provides JvmStatic]
    fun provideRecipesService(): RecipesService =
        Retrofit.Builder()
            .baseUrl("http://localhost:3000/") // TODO: Temporary until I create the real domain
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipesService::class.java)
}

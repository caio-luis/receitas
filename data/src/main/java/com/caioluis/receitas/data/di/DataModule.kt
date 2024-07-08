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
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @[Provides Singleton]
    fun provideRecipesDataSource(
        localImpl: LocalSource,
        remoteImpl: RemoteSource,
    ): RecipesDataSource = RecipesDataSourceImpl(localImpl, remoteImpl)

    @[Provides Singleton]
    fun provideRecipesDao(
        @ApplicationContext context: Context
    ): RecipesDao =
        RecipesDataBase.getInstance(context).recipesDao()

    @[Provides Singleton]
    fun provideRecipesLocalMapper(): RecipesLocalMapper =
        RecipesLocalMapper.Impl()

    @[Provides Singleton]
    fun provideIngredientsSearchSqlQueryMapper(): IngredientsSearchSqlQueryMapper =
        IngredientsSearchSqlQueryMapper.Impl()

    @[Provides Singleton]
    fun provideRecipesRemoteMapper(): RecipesRemoteMapper =
        RecipesRemoteMapperImpl()

    @[Provides Singleton]
    fun provideLocalImpl(
        dao: RecipesDao,
        mapper: RecipesLocalMapper,
        queryMapperIngredientsSearch: IngredientsSearchSqlQueryMapper
    ): LocalSource =
        LocalImpl(dao, mapper, queryMapperIngredientsSearch)

    @[Provides Singleton]
    fun provideRemoteImpl(
        service: RecipesService,
        remoteMapperImpl: RecipesRemoteMapper
    ): RemoteSource =
        RemoteImpl(service, remoteMapperImpl)

    @[Provides Singleton]
    fun provideRecipesService(): RecipesService =
        Retrofit.Builder()
            .baseUrl("http://localhost:3000/") // TODO: Temporary until I create the real domain
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipesService::class.java)
}

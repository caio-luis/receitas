package com.caioluis.receitas.di

import com.caioluis.receitas.RecipesApplication
import com.caioluis.receitas.data.di.DataModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(modules = [RecipesModule::class, AppModule::class, DataModule::class])
interface AppComponent : AndroidInjector<RecipesApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: RecipesApplication): AndroidInjector<RecipesApplication>?
    }
}
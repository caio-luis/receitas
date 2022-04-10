package com.caioluis.receitas.di

import android.content.Context
import com.caioluis.receitas.RecipesApplication
import com.caioluis.receitas.presentation.ui.RecipesActivity
import com.caioluis.receitas.presentation.ui.SplashActivity
import dagger.Binds
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector
import javax.inject.Singleton

@Module(includes = [AndroidInjectionModule::class])
abstract class AppModule {
    @[Binds Singleton]
    abstract fun context(app: RecipesApplication): Context

    @ContributesAndroidInjector
    abstract fun injectRecipesActivity(): RecipesActivity

    @ContributesAndroidInjector
    abstract fun injectSplashActivity(): SplashActivity
}
package com.caioluis.receitas

import android.app.Application
import com.caioluis.receitas.di.AppComponent
import com.caioluis.receitas.di.DaggerAppComponent
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

open class RecipesApplication : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return (DaggerAppComponent.factory().create(this) as AppComponent).apply {
            inject(this@RecipesApplication)
        }
    }

    override fun androidInjector() = androidInjector
}
package com.caioluis.receitas

import android.app.Application
import com.caioluis.receitas.di.AppComponent
import com.caioluis.receitas.di.DaggerAppComponent

open class RecipesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initializeComponent()
    }

    open fun initializeComponent(): AppComponent {
        return DaggerAppComponent.factory().create(applicationContext)
    }
}
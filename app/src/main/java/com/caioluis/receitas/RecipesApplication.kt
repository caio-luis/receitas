package com.caioluis.receitas

import android.app.Application
import com.caioluis.receitas.data.local.database.RecipesDao
import com.caioluis.receitas.data.model.Recipe
import com.caioluis.receitas.di.AppComponent
import com.caioluis.receitas.di.DaggerAppComponent
import com.caioluis.receitas.util.BaseSchedulerProvider
import com.caioluis.receitas.util.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import io.reactivex.Observable
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
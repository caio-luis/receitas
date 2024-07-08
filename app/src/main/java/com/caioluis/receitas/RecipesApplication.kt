package com.caioluis.receitas

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class RecipesApplication : Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
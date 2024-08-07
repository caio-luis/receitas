package com.caioluis.receitas.presentation.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.caioluis.receitas.R
import com.caioluis.receitas.data.local.database.DataBaseConstants
import com.caioluis.receitas.data.local.database.RecipesDao
import com.caioluis.receitas.data.local.model.Recipe
import com.caioluis.receitas.util.BaseSchedulerProvider
import com.caioluis.receitas.util.getJsonDataFromAsset
import com.caioluis.receitas.util.showToast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity(R.layout.activity_splash) {
    private val disposable: CompositeDisposable = CompositeDisposable()

    @Inject
    lateinit var recipesDao: RecipesDao

    @Inject
    lateinit var schedulerProvider: BaseSchedulerProvider

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        populateDatabaseFromJsonAsset()
    }

    private fun populateDatabaseFromJsonAsset() {
        val recipesJsonString = getJsonDataFromAsset(applicationContext, "receitas.json")

        val startAppAction = {
            startActivity(Intent(this, RecipesActivity::class.java))
            finish()
        }

        if (applicationContext.getDatabasePath(DataBaseConstants.DATABASE_NAME).exists()) {
            startAppAction()
        } else {
            val recipes = Gson().fromJson<List<Recipe>>(
                recipesJsonString,
                object : TypeToken<List<Recipe>>() {}.type
            ).filter { it.ingredients.orEmpty().isNotEmpty() && it.sections.orEmpty().isNotEmpty() }

            disposable += Observable.just(Unit)
                .subscribeOn(schedulerProvider.io())
                .doOnError { showToast(it.message.toString()) }
                .doOnComplete(startAppAction)
                .subscribe { recipesDao.insertRecipes(recipes) }
        }
    }

    override fun onDestroy() {
        if (disposable.isDisposed)
            disposable.dispose()
        super.onDestroy()
    }
}

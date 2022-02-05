package com.caioluis.receitas.presentation.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caioluis.receitas.R
import com.caioluis.receitas.presentation.model.RecipeViewModel
import com.caioluis.receitas.presentation.structure.RecipesPresenter
import com.caioluis.receitas.presentation.ui.components.BasicActivityLayout
import com.caioluis.receitas.presentation.ui.components.BasicAppBar
import com.caioluis.receitas.presentation.ui.components.ChipGroup
import com.caioluis.receitas.presentation.ui.components.RecipesList
import com.caioluis.receitas.presentation.ui.components.SearchBoxComponent
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

@ExperimentalMaterialApi
class RecipesComposeActivity : ComponentActivity() {

//    @Inject
//    lateinit var recipesPresenter: RecipesPresenter
//
//    private val recipesObservable = mutableStateListOf<RecipeViewModel>()
//    private val ingredientsToSearchObservable = mutableStateListOf<String>()
//    private val disposable = CompositeDisposable()
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        AndroidInjection.inject(this)
//        super.onCreate(savedInstanceState)
//        setContent { RecipesActivityLayout() }
//        renderState()
//    }
//
//    private fun renderState() {
//        disposable += recipesPresenter.stateSubject.subscribe {
//            when {
//                it.error != null -> it.error.message?.let { error -> showToast(error) }
//
//                it.recipes != recipesObservable -> {
//                    recipesObservable.clear()
//                    recipesObservable.addAll(it.recipes)
//                }
//
//                it.ingredients != ingredientsToSearchObservable -> {
//                    ingredientsToSearchObservable.clear()
//                    ingredientsToSearchObservable.addAll(it.ingredients)
//                }
//            }
//        }
//    }
//
//    @Preview
//    @Composable
//    fun RecipesActivityLayout() {
//        BasicActivityLayout(
//            appBar = { BasicAppBar(title = stringResource(R.string.app_name)) }
//        ) {
//            Column(
//                horizontalAlignment = Alignment.CenterHorizontally,
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 8.dp)
//            ) {
//
//                SearchBoxComponent(
//                    clearTextAfterSearch = true,
//                    hint = "Coloque nome do ingrediente",
//                    buttonAction = {
//                        dispatchUiEvent(RecipeUiEvent.IngredientAdded(it))
//                    }
//                )
//
//                val ingredients = remember { ingredientsToSearchObservable }
//                ChipGroup(ingredients = ingredients)
//
//                val list = remember { recipesObservable }
//                RecipesList(
//                    recipes = list,
//                    itemClick = { showToast(it.recipeName) }
//                )
//            }
//        }
//    }
//
//    private fun dispatchUiEvent(event: RecipeUiEvent) {
//        recipesPresenter.dispatchCommand(event)
//    }
//
//    private fun showToast(text: String) {
//        Toast.makeText(this@RecipesComposeActivity, text, Toast.LENGTH_SHORT)
//            .show()
//    }
//
//    override fun onDestroy() {
//        recipesPresenter.clear()
//        if (disposable.isDisposed) disposable.dispose()
//        super.onDestroy()
//    }
}

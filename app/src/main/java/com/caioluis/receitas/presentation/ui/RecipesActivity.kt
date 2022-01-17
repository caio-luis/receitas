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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.caioluis.receitas.R
import com.caioluis.receitas.presentation.structure.RecipesPresenter
import com.caioluis.receitas.presentation.ui.components.BasicActivityLayout
import com.caioluis.receitas.presentation.ui.components.BasicAppBar
import com.caioluis.receitas.presentation.ui.components.RecipesList
import com.caioluis.receitas.presentation.ui.components.SearchBoxComponent
import com.caioluis.receitas.util.fakeRecipes
import javax.inject.Inject

@ExperimentalMaterialApi
class RecipesActivity : ComponentActivity() {

    @Inject
    lateinit var recipesPresenter: RecipesPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { RecipesActivityLayout() }
    }

    @Preview
    @Composable
    fun RecipesActivityLayout() {
        BasicActivityLayout(
            appBar = { BasicAppBar(title = stringResource(R.string.app_name)) }
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 8.dp)
            ) {

                SearchBoxComponent(
                    clearTextAfterSearch = true,
                    hint = "Coloque nome do ingrediente",
                    buttonAction = {
//                        dispatchUiEvent(RecipeUiEvent.IngredientAdded(it))
                    }
                )

                RecipesList(
                    recipes = fakeRecipes,
                    itemClick = { showToast(it.recipeName) }
                )
            }
        }
    }

    private fun dispatchUiEvent(event: RecipeUiEvent) {
        recipesPresenter.dispatchCommand(event)
    }

    private fun showToast(text: String) {
        Toast.makeText(this@RecipesActivity, text, Toast.LENGTH_SHORT)
            .show()
    }

    override fun onDestroy() {
        recipesPresenter.clear()
        super.onDestroy()
    }
}

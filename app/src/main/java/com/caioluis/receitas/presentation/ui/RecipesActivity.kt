package com.caioluis.receitas.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.caioluis.receitas.R
import com.caioluis.receitas.presentation.RecipesAdapter
import com.caioluis.receitas.presentation.model.RecipeViewModel
import com.caioluis.receitas.presentation.structure.RecipesPresenter
import com.caioluis.receitas.presentation.structure.RecipesState
import com.caioluis.receitas.presentation.viewcomponent.IngredientsChipGroup
import com.caioluis.receitas.util.hide
import com.caioluis.receitas.util.show
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class RecipesActivity : AppCompatActivity(R.layout.activity_recipes) {

    @Inject
    lateinit var recipesPresenter: RecipesPresenter

    private val disposable = CompositeDisposable()
    private val recipesAdapter = RecipesAdapter()

    private val loadingView: LinearLayout by lazy { findViewById(R.id.loading_screen) }
    private val recipesRecyclerView: RecyclerView by lazy { findViewById(R.id.recipes_list) }
    private val searchTextInputLayout: TextInputLayout by lazy { findViewById(R.id.search_ingredient_layout) }
    private val searchTextInput: TextInputEditText by lazy { findViewById(R.id.search_ingredient_edit_text) }
    private val ingredientsChipGroup: IngredientsChipGroup by lazy { findViewById(R.id.ingredients_chip_group) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        observeState()
        setList()
        setListeners()
    }

    private fun setListeners() {
        searchTextInputLayout.setEndIconOnClickListener {
            searchTextInput.text?.let {
                if (it.isNotBlank()) {
                    dispatchUiEvent(RecipeUiEvent.IngredientAdded(it.toString()))
                    it.clear()
                }
            }
        }

        disposable += ingredientsChipGroup.closeIconClickOutput.subscribe {
            dispatchUiEvent(RecipeUiEvent.IngredientRemoved(it))
        }
    }

    private fun observeState() {
        disposable += recipesPresenter.stateSubject.subscribe { state ->

            setLoadingVisibility(state.loading)
            setRecipesList(state.recipes)
            setIngredientsList(state)

            when {
                state.error != null -> state.error.message?.let { error -> showToast(error) }
                state.ingredients.limitReached -> showToast(getString(R.string.ingredients_limit_reached))
            }
        }
    }

    private fun setIngredientsList(state: RecipesState) {
        ingredientsChipGroup.show()
        ingredientsChipGroup.renderChips(state.ingredients.ingredientsToSearch)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRecipesList(recipes: List<RecipeViewModel>) {
        when {
            recipes.isNotEmpty() -> {
                recipesAdapter.submitList(recipes)
                recipesAdapter.notifyDataSetChanged()
            }
            else -> {
                recipesAdapter.submitList(listOf())
                recipesAdapter.notifyDataSetChanged()
            }
        }
    }

    private fun setLoadingVisibility(loading: Boolean) {
        when {
            loading -> loadingView.show()
            else -> loadingView.hide()
        }
    }

    private fun setList() {
        recipesRecyclerView.adapter = recipesAdapter
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
        if (disposable.isDisposed) disposable.dispose()
        super.onDestroy()
    }
}
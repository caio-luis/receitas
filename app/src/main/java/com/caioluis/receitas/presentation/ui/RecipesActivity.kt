package com.caioluis.receitas.presentation.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.caioluis.receitas.R
import com.caioluis.receitas.notifications.bridge.NotificationDispatcher
import com.caioluis.receitas.presentation.adapter.RecipesAdapter
import com.caioluis.receitas.presentation.model.RecipeViewModel
import com.caioluis.receitas.presentation.structure.RecipesPresenter
import com.caioluis.receitas.presentation.structure.RecipesState
import com.caioluis.receitas.presentation.viewcomponent.IngredientsChipGroup
import com.caioluis.receitas.presentation.viewcomponent.SearchAddInputTextComponent
import com.caioluis.receitas.util.hide
import com.caioluis.receitas.util.show
import com.caioluis.receitas.util.showToast
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import javax.inject.Inject

class RecipesActivity : AppCompatActivity(R.layout.activity_recipes) {

    @Inject
    lateinit var recipesPresenter: RecipesPresenter

    @Inject
    lateinit var notificationDispatcher: NotificationDispatcher

    private val disposable = CompositeDisposable()
    private val recipesAdapter = RecipesAdapter()

    private var recipesState = RecipesState()

    private val loadingView: LinearLayout by lazy { findViewById(R.id.loading_screen) }
    private val recipesRecyclerView: RecyclerView by lazy { findViewById(R.id.recipes_list) }
    private val searchAddTextInput: SearchAddInputTextComponent by lazy { findViewById(R.id.search_ingredient_layout) }
    private val ingredientsChipGroup: IngredientsChipGroup by lazy { findViewById(R.id.ingredients_chip_group) }

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        observeState()
        setAdapter()
        subscribeClickEvents()
    }

    private fun subscribeClickEvents() {
        disposable += ingredientsChipGroup.closeIconClickOutput.subscribe {
            dispatchUiEvent(RecipeUiEvent.IngredientRemoved(it))
        }

        disposable += searchAddTextInput.addButtonOutput.subscribe {
            dispatchUiEvent(RecipeUiEvent.IngredientAdded(it.toString()))
        }

        disposable += searchAddTextInput.searchButtonOutput.subscribe {
            if (recipesState.ingredients.ingredientsToSearch.isNotEmpty()) {
                dispatchUiEvent(RecipeUiEvent.Search(recipesState.ingredients.ingredientsToSearch))
            }
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
            recipesState = state
        }
    }

    private fun setIngredientsList(state: RecipesState) {
        ingredientsChipGroup.show()
        ingredientsChipGroup.renderChips(state.ingredients.ingredientsToSearch)
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setRecipesList(recipes: List<RecipeViewModel>) {
        if (recipesAdapter.currentList != recipes) {
            recipesAdapter.submitList(recipes)
            recipesAdapter.notifyDataSetChanged()
        }
    }

    private fun setLoadingVisibility(loading: Boolean) {
        when {
            loading -> loadingView.show()
            else -> loadingView.hide()
        }
    }

    private fun setAdapter() {
        recipesRecyclerView.adapter = recipesAdapter
    }

    private fun dispatchUiEvent(event: RecipeUiEvent) {
        recipesPresenter.dispatchCommand(event)
    }

    override fun onDestroy() {
        recipesPresenter.clear()
        if (disposable.isDisposed) disposable.dispose()
        super.onDestroy()
    }
}

package com.caioluis.receitas.presentation.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.caioluis.receitas.R
import com.caioluis.receitas.presentation.adapter.RecipeDetailsAdapter
import com.caioluis.receitas.presentation.mapper.RecipeDetailsMapper
import com.caioluis.receitas.presentation.model.RecipeViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeDetailsActivity : AppCompatActivity(R.layout.activity_recipe_details) {

    private val recipeDetailsList: RecyclerView by lazy { findViewById(R.id.recipe_details_list) }
    private val recipeDetailsAdapter = RecipeDetailsAdapter()

    @Inject
    lateinit var recipeDetailsMapper: RecipeDetailsMapper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeDetailsList.adapter = recipeDetailsAdapter

        val list = intent?.getSerializableExtra(RECIPE_DETAILS_EXTRA)?.let {
            recipeDetailsMapper.map(it as RecipeViewModel)
        }

        recipeDetailsAdapter.submitList(list)
    }

    companion object {

        private const val RECIPE_DETAILS_EXTRA = "recipe_details_extra"

        fun createIntent(recipe: RecipeViewModel, context: Context): Intent {
            return Intent(context, RecipeDetailsActivity::class.java).putExtra(
                RECIPE_DETAILS_EXTRA,
                recipe
            )
        }
    }
}
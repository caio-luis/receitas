package com.caioluis.receitas.presentation.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caioluis.receitas.presentation.model.RecipeDetailViewModel
import com.caioluis.receitas.presentation.viewcomponent.RecipeDetailComponent

class RecipeDetailsAdapter :
    ListAdapter<RecipeDetailViewModel, RecipeDetailsAdapter.RecipesDetailsViewHolder>(
        RECIPES_COMPARATOR
    ) {

    inner class RecipesDetailsViewHolder(private val view: RecipeDetailComponent) :
        RecyclerView.ViewHolder(view) {
        fun bind(recipeDetailViewModel: RecipeDetailViewModel) {
            view.render(recipeDetailViewModel)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesDetailsViewHolder {
        val view = RecipeDetailComponent(parent.context)
        return RecipesDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipesDetailsViewHolder, position: Int) {
        val recipe: RecipeDetailViewModel? = getItem(position)
        if (recipe != null)
            holder.bind(recipe)
    }

    companion object {
        private val RECIPES_COMPARATOR = object : DiffUtil.ItemCallback<RecipeDetailViewModel>() {
            override fun areItemsTheSame(
                oldItem: RecipeDetailViewModel,
                newItem: RecipeDetailViewModel
            ): Boolean {
                return oldItem.text == newItem.text
            }

            override fun areContentsTheSame(
                oldItem: RecipeDetailViewModel,
                newItem: RecipeDetailViewModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}

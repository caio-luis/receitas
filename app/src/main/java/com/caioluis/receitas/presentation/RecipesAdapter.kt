package com.caioluis.receitas.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caioluis.receitas.presentation.model.RecipeViewModel

class RecipesAdapter :
    ListAdapter<RecipeViewModel, RecipesAdapter.RecipesViewHolder>(RECIPES_COMPARATOR) {

    inner class RecipesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(recipe: RecipeViewModel) {
            val title = itemView.findViewById<TextView>(android.R.id.text1)
            title.text = recipe.recipeName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(android.R.layout.simple_list_item_1, parent, false)
        return RecipesViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecipesViewHolder, position: Int) {
        val recipe: RecipeViewModel? = getItem(position)
        if (recipe != null) holder.bind(recipe)
    }

    companion object {
        private val RECIPES_COMPARATOR = object : DiffUtil.ItemCallback<RecipeViewModel>() {
            override fun areItemsTheSame(
                oldItem: RecipeViewModel,
                newItem: RecipeViewModel
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: RecipeViewModel,
                newItem: RecipeViewModel
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
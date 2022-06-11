package com.caioluis.receitas.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.caioluis.receitas.R
import com.caioluis.receitas.presentation.model.RecipeViewModel
import com.caioluis.receitas.presentation.ui.RecipeDetailsActivity

class RecipesAdapter :
    ListAdapter<RecipeViewModel, RecipesAdapter.RecipesViewHolder>(RECIPES_COMPARATOR) {

    inner class RecipesViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(recipe: RecipeViewModel) {
            val title = itemView.findViewById<TextView>(R.id.recipe_title)
            title.text = recipe.recipeName

            title.setOnClickListener {
                val intent = RecipeDetailsActivity.createIntent(recipe, itemView.context)
                itemView.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipesViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.recipe_list_item, parent, false)
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
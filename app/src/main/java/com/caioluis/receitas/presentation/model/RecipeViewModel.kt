package com.caioluis.receitas.presentation.model

data class RecipeViewModel(
        val id: String,
        val recipeName: String,
        val sections: List<RecipeSectionViewModel>
)
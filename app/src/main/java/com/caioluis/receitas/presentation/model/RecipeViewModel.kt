package com.caioluis.receitas.presentation.model

import java.io.Serializable

data class RecipeViewModel(
    val id: String,
    val recipeName: String,
    val sections: List<RecipeSectionViewModel>,
    val ingredients: List<String>,
) : Serializable
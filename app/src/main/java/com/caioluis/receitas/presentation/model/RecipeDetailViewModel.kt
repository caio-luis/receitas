package com.caioluis.receitas.presentation.model

data class RecipeDetailViewModel(
    val text: String,
    val type: DetailType
)

enum class DetailType {
    TITLE,
    SECTION_NAME,
    DESCRIPTION
}

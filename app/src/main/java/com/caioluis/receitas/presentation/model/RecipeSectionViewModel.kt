package com.caioluis.receitas.presentation.model

import java.io.Serializable

data class RecipeSectionViewModel(
    val sectionName: String,
    val content: List<String>
) : Serializable

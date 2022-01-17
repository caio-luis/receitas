package com.caioluis.receitas.util

import com.caioluis.receitas.presentation.model.RecipeSectionViewModel
import com.caioluis.receitas.presentation.model.RecipeViewModel

val fakeRecipes = listOf(
    RecipeViewModel(
        id = "a",
        recipeName = "Manjar",
        sections = listOf(
            RecipeSectionViewModel(
                sectionName = "ingredientes",
                content = listOf("um", "dois")
            )
        )
    ),
    RecipeViewModel(
        id = "b",
        recipeName = "Pudim",
        sections = listOf(
            RecipeSectionViewModel(
                sectionName = "ingredientes",
                content = listOf("um", "dois")
            )
        )
    ),
    RecipeViewModel(
        id = "c",
        recipeName = "Torta",
        sections = listOf(
            RecipeSectionViewModel(
                sectionName = "ingredientes",
                content = listOf("um", "dois")
            )
        )
    ),
    RecipeViewModel(
        id = "d",
        recipeName = "Bolo de Cenoura",
        sections = listOf(
            RecipeSectionViewModel(
                sectionName = "ingredientes",
                content = listOf("um", "dois")
            )
        )
    ),
    RecipeViewModel(
        id = "e",
        recipeName = "Pizza",
        sections = listOf(
            RecipeSectionViewModel(
                sectionName = "ingredientes",
                content = listOf("um", "dois")
            )
        )
    )
)
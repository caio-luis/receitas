package com.caioluis.receitas

import com.caioluis.receitas.data.local.model.Recipe
import com.caioluis.receitas.presentation.model.DetailType
import com.caioluis.receitas.presentation.model.RecipeDetailViewModel
import com.caioluis.receitas.presentation.model.RecipeSectionViewModel
import com.caioluis.receitas.presentation.model.RecipeViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Fixtures {

    fun getRecipesMockFromJsonResource(): List<Recipe> {
        val rawJson = MockJsonParser().readFile("json/recipes-test.json")

        return Gson().fromJson(
            rawJson,
            object : TypeToken<List<Recipe>>() {}.type
        )
    }

    fun recipeDetailList() = listOf(
        RecipeDetailViewModel("Bolo de cenoura", DetailType.TITLE),
        RecipeDetailViewModel("Ingredientes", DetailType.SECTION_NAME),
        RecipeDetailViewModel("ovo", DetailType.DESCRIPTION),
        RecipeDetailViewModel("farinha", DetailType.DESCRIPTION),
        RecipeDetailViewModel("Modo de preparo", DetailType.SECTION_NAME),
        RecipeDetailViewModel("Misture tudo", DetailType.DESCRIPTION),
        RecipeDetailViewModel("Leve ao forno por 10 minutos", DetailType.DESCRIPTION),
    )

    val recipeViewModel = RecipeViewModel(
        id = "",
        recipeName = "Bolo de cenoura",
        sections = listOf(
            RecipeSectionViewModel(
                sectionName = "Modo de preparo",
                content = listOf("Misture tudo", "Leve ao forno por 10 minutos")
            )
        ),
        ingredients = listOf("ovo", "farinha")
    )
}
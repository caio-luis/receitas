package com.caioluis.receitas

import com.caioluis.receitas.data.model.Recipe
import com.caioluis.receitas.data.model.Section
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

    val recipes = listOf(
        Recipe(
            id = "abc",
            recipeName = "receita 1",
            sections = listOf(
                Section(
                    sectionName = "Modo de preparo",
                    content = listOf(
                        "Bata o ovo.",
                        "Adicione o leite."
                    )
                )
            ),
            ingredients = listOf(
                "1 ovo",
                "500 ml de leite"
            )
        ),
        Recipe(
            id = "def",
            recipeName = "receita 2",
            sections = listOf(
                Section(
                    sectionName = "Modo de preparo",
                    content = listOf(
                        "Adicione o sal",
                        "Prepare a carne"
                    )
                )
            ),
            ingredients = listOf(
                "sal",
                "1kg de carne"
            )
        ),
        Recipe(
            id = "ghi",
            recipeName = "receita 3",
            sections = listOf(
                Section(
                    sectionName = "Modo de preparo",
                    content = listOf(
                        "Adicione o fermento",
                        "Adicione a farinha"
                    )
                )
            ),
            ingredients = listOf(
                "Uma pitada de fermento",
                "200g de farinha"
            )
        )
    )
}
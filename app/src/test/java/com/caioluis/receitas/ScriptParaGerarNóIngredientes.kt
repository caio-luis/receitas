package com.caioluis.receitas

import com.caioluis.receitas.data.model.Recipe
import com.caioluis.receitas.data.model.Section
import com.google.gson.GsonBuilder
import com.google.gson.annotations.SerializedName
import java.util.*

class ScriptParaGerarNóIngredientes {
//    @Test
//    fun testando() {
//        println()
//
//        val jsonList = teste()
//
//        File("receitas.json").writeText(jsonList)
//    }

    fun generateJson(): String {
        val entities = Fixtures.getRecipesMockFromJsonResource()

        val list = entities.map {

            val ingredients = mutableListOf<String>()
            var recipe: Recipe = it

            it.sections?.map { section ->
                if (section.sectionName?.trim()?.lowercase(Locale.getDefault()) == "ingredientes") {
                    section.content?.let { ingredients.addAll(it) }
                    val sections = it.sections?.toMutableSet()
                    sections?.remove(section)
                    recipe = it.copy(it.id, it.recipeName, sections?.toList())
                }
            }

            ingredients.removeIf { it.isBlank() }

            RecipeNew(
                recipe.id,
                recipe.recipeName,
                recipe.sections,
                ingredients = ingredients
            )
        }

        return GsonBuilder().setPrettyPrinting().create().toJson(list)
    }

    inner class RecipeNew(
        @SerializedName("id")
        val id: String,

        @SerializedName("nome")
        val recipeName: String? = "",

        @SerializedName("secao")
        val sections: List<Section>? = listOf(),

        @SerializedName("ingredientes")
        val ingredients: List<String>? = listOf()
    )
}
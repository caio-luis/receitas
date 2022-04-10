package com.caioluis.receitas

import com.caioluis.receitas.data.local.model.Recipe
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
}
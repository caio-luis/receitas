package com.caioluis.receitas.presentation.mapper

import com.caioluis.receitas.Fixtures.recipeDetailList
import com.caioluis.receitas.Fixtures.recipeViewModel
import com.caioluis.receitas.presentation.mapper.RecipeDetailsMapper.RecipeDetailsMapperImpl
import org.junit.Assert.assertEquals
import org.junit.Test

class RecipeDetailsMapperTest {

    private val recipeDetailsMapper = RecipeDetailsMapperImpl()

    @Test
    fun `assert it maps to recipe details correctly and with right order`() {

        assertEquals(
            recipeDetailList(),
            recipeDetailsMapper.map(recipeViewModel)
        )
    }
}
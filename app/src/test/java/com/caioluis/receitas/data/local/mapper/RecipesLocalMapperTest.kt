package com.caioluis.receitas.data.local.mapper

import com.caioluis.receitas.Fixtures
import org.junit.Test

class RecipesLocalMapperTest {

    private val recipesLocalMapper = RecipesLocalMapper.Impl()

    @Test
    fun `assert that map to domain correctly`() {

        Fixtures.getRecipesMockFromJsonResource()
    }
}
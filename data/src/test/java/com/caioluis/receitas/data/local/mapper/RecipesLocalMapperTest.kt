package com.caioluis.receitas.data.local.mapper

import com.caioluis.receitas.data.local.Fixtures
import org.junit.Assert.assertEquals
import org.junit.Test

class RecipesLocalMapperTest {

    private val recipesLocalMapper = RecipesLocalMapper.Impl()

    @Test
    fun `assert that map Section to DomainSection correctly`() {

        val sections = Fixtures.recipes.flatMap { it.sections!! }
        val mappedSection = recipesLocalMapper.mapRecipesSections(sections)

        assertEquals(Fixtures.domainSections, mappedSection)
    }

    @Test
    fun `assert that map Recipe to DomainRecipe correctly`() {

        val mappedRecipes = recipesLocalMapper.mapRecipes(Fixtures.recipes)

        assertEquals(Fixtures.domainRecipes, mappedRecipes)
    }
}
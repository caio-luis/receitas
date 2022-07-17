package com.caioluis.receitas.data.local.mapper

import com.caioluis.receitas.domain.model.DomainRecipe
import com.caioluis.receitas.domain.model.DomainSection
import com.caioluis.receitas.data.local.model.Recipe
import com.caioluis.receitas.data.local.model.Section

interface RecipesLocalMapper {

    fun mapRecipes(recipes: List<Recipe>): List<DomainRecipe>
    fun mapRecipesSections(section: List<Section>): List<DomainSection>

    class Impl : RecipesLocalMapper {

        override fun mapRecipes(recipes: List<Recipe>): List<DomainRecipe> {
            return recipes.map {
                DomainRecipe(
                    id = it.id,
                    recipeName = it.recipeName,
                    sections = mapRecipesSections(it.sections.orEmpty()),
                    ingredients = it.ingredients
                )
            }
        }

        override fun mapRecipesSections(section: List<Section>): List<DomainSection> {
            return section.map { DomainSection(it.sectionName, it.content) }
        }
    }
}

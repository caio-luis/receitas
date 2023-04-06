package com.caioluis.receitas.data.remote.mapper

import com.caioluis.receitas.data.remote.model.RecipeRemote
import com.caioluis.receitas.domain.model.DomainRecipe
import com.caioluis.receitas.domain.model.DomainSection

class RecipesRemoteMapperImpl : RecipesRemoteMapper {
    override fun mapRecipes(recipes: List<RecipeRemote>): List<DomainRecipe> {
        return recipes.map {
            DomainRecipe(
                id = it.id,
                ingredients = it.ingredients,
                recipeName = it.recipeName,
                sections = it.sections?.map {
                    DomainSection(
                        sectionName = it.sectionName,
                        content = it.content,
                    )
                },
            )
        }
    }
}
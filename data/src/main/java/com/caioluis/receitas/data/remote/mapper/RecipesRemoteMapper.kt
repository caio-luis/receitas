package com.caioluis.receitas.data.remote.mapper

import com.caioluis.receitas.data.remote.model.RecipeRemote
import com.caioluis.receitas.domain.model.DomainRecipe

interface RecipesRemoteMapper {
    fun mapRecipes(recipes: List<RecipeRemote>): List<DomainRecipe>
}

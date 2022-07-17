package com.caioluis.receitas.presentation.mapper

import com.caioluis.receitas.domain.model.DomainRecipe
import com.caioluis.receitas.domain.model.DomainSection
import com.caioluis.receitas.presentation.model.RecipeSectionViewModel
import com.caioluis.receitas.presentation.model.RecipeViewModel

fun DomainRecipe.toViewModel(): RecipeViewModel {
    return RecipeViewModel(
        id = id.orEmpty(),
        recipeName = recipeName.orEmpty(),
        sections = sections?.map { it.toViewModel() }.orEmpty(),
        ingredients = ingredients.orEmpty()
    )
}

fun DomainSection.toViewModel(): RecipeSectionViewModel {
    return RecipeSectionViewModel(sectionName.orEmpty(), content.orEmpty())
}

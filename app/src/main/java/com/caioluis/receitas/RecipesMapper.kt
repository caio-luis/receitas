package com.caioluis.receitas

import com.caioluis.receitas.data.local.model.Recipe
import com.caioluis.receitas.data.local.model.Section
import com.caioluis.receitas.domain.model.DomainRecipe
import com.caioluis.receitas.domain.model.DomainSection

fun Recipe.toDomain(): DomainRecipe {
    return DomainRecipe(
        id = id,
        recipeName = recipeName,
        sections = sections?.map { it.toDomain() }
    )
}

fun Section.toDomain(): DomainSection {
    return DomainSection(sectionName, content)
}
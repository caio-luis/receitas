package com.caioluis.receitas.data

import com.caioluis.receitas.data.local.model.Recipe
import com.caioluis.receitas.data.local.model.Section
import com.caioluis.receitas.domain.model.DomainRecipe
import com.caioluis.receitas.domain.model.DomainSection

object Fixtures {
    val recipes = listOf(
        Recipe(
            id = "abc",
            recipeName = "receita 1",
            sections = listOf(
                Section(
                    sectionName = "Modo de preparo",
                    content = listOf(
                        "Bata o ovo.",
                        "Adicione o leite."
                    )
                )
            ),
            ingredients = listOf(
                "1 ovo",
                "500 ml de leite"
            )
        ),
        Recipe(
            id = "def",
            recipeName = "receita 2",
            sections = listOf(
                Section(
                    sectionName = "Modo de preparo",
                    content = listOf(
                        "Adicione o sal",
                        "Prepare a carne"
                    )
                )
            ),
            ingredients = listOf(
                "sal",
                "1kg de carne"
            )
        ),
        Recipe(
            id = "ghi",
            recipeName = "receita 3",
            sections = listOf(
                Section(
                    sectionName = "Modo de preparo",
                    content = listOf(
                        "Adicione o fermento",
                        "Adicione a farinha"
                    )
                )
            ),
            ingredients = listOf(
                "Uma pitada de fermento",
                "200g de farinha"
            )
        )
    )

    val domainRecipes = listOf(
        DomainRecipe(
            id = "abc",
            recipeName = "receita 1",
            sections = listOf(
                DomainSection(
                    sectionName = "Modo de preparo",
                    content = listOf(
                        "Bata o ovo.",
                        "Adicione o leite."
                    )
                )
            ),
            ingredients = listOf(
                "1 ovo",
                "500 ml de leite"
            )
        ),
        DomainRecipe(
            id = "def",
            recipeName = "receita 2",
            sections = listOf(
                DomainSection(
                    sectionName = "Modo de preparo",
                    content = listOf(
                        "Adicione o sal",
                        "Prepare a carne"
                    )
                )
            ),
            ingredients = listOf(
                "sal",
                "1kg de carne"
            )
        ),
        DomainRecipe(
            id = "ghi",
            recipeName = "receita 3",
            sections = listOf(
                DomainSection(
                    sectionName = "Modo de preparo",
                    content = listOf(
                        "Adicione o fermento",
                        "Adicione a farinha"
                    )
                )
            ),
            ingredients = listOf(
                "Uma pitada de fermento",
                "200g de farinha"
            )
        )
    )

    val domainSections = listOf(
        DomainSection(
            sectionName = "Modo de preparo",
            content = listOf(
                "Bata o ovo.",
                "Adicione o leite."
            )
        ),
        DomainSection(
            sectionName = "Modo de preparo",
            content = listOf(
                "Adicione o sal",
                "Prepare a carne"
            )
        ),
        DomainSection(
            sectionName = "Modo de preparo",
            content = listOf(
                "Adicione o fermento",
                "Adicione a farinha"
            )
        )
    )
}

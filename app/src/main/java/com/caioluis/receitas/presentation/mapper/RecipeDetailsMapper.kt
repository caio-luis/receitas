package com.caioluis.receitas.presentation.mapper

import com.caioluis.receitas.presentation.model.DetailType
import com.caioluis.receitas.presentation.model.RecipeDetailViewModel
import com.caioluis.receitas.presentation.model.RecipeSectionViewModel
import com.caioluis.receitas.presentation.model.RecipeViewModel

interface RecipeDetailsMapper {
    fun map(recipe: RecipeViewModel): List<RecipeDetailViewModel>

    class RecipeDetailsMapperImpl : RecipeDetailsMapper {
        private val recipeDetails = mutableListOf<RecipeDetailViewModel>()

        override fun map(recipe: RecipeViewModel): List<RecipeDetailViewModel> {

            addItemToDetailsList(recipe.recipeName, DetailType.TITLE)
            addIngredients(recipe.ingredients)
            addSections(recipe.sections)

            return recipeDetails
        }

        private fun addIngredients(ingredients: List<String>) {
            ingredients
                .takeIf { it.isNotEmpty() }
                ?.also { addItemToDetailsList("Ingredientes", DetailType.SECTION_NAME) }
                ?.map {
                    addItemToDetailsList(it, DetailType.DESCRIPTION)
                }
        }

        private fun addSections(sections: List<RecipeSectionViewModel>) {
            sections
                .takeIf { it.isNotEmpty() }
                ?.map {
                    addItemToDetailsList(it.sectionName, DetailType.SECTION_NAME)
                    addContent(it.content)
                }
        }

        private fun addContent(contents: List<String>) {
            contents
                .takeIf { it.isNotEmpty() }
                ?.map { content ->
                    recipeDetails.add(
                        RecipeDetailViewModel(
                            content,
                            DetailType.DESCRIPTION
                        )
                    )
                }
        }

        private fun addItemToDetailsList(detail: String, type: DetailType) {
            recipeDetails.add(RecipeDetailViewModel(detail, type))
        }
    }
}

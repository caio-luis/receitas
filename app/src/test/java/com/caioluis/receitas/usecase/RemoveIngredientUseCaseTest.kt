package com.caioluis.receitas.usecase

import com.caioluis.receitas.bridge.model.IngredientsToSearch
import com.caioluis.receitas.bridge.usecase.RemoveIngredientUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class RemoveIngredientUseCaseTest {
    private val removeIngredientUseCase: RemoveIngredientUseCase
        get() = RemoveIngredientUseCaseImpl()

    @Test
    fun `assert that do nothing when try to remove an ingredient from empty list`() {
        assertEquals(
            IngredientsToSearch(),
            removeIngredientUseCase.invoke(
                IngredientsToSearch(),
                "teste"
            )
        )
    }

    @Test
    fun `assert that removes an ingredient from list correctly`() {
        assertEquals(
            IngredientsToSearch(),
            removeIngredientUseCase.invoke(
                IngredientsToSearch(mutableListOf("teste")),
                "teste"
            )
        )
    }

    @Test
    fun `assert that removes an ingredient from list when limit reached correctly`() {
        assertEquals(
            IngredientsToSearch(mutableListOf("1", "3", "4", "5")),
            removeIngredientUseCase.invoke(
                IngredientsToSearch(
                    ingredientsToSearch = mutableListOf("1", "2", "3", "4", "5"),
                    limitReached = true
                ),
                "2"
            )
        )
    }

    @Test
    fun `assert that do nothing when try to remove an ingredient that is not in the list`() {
        assertEquals(
            IngredientsToSearch(mutableListOf("1", "2", "3", "4")),
            removeIngredientUseCase.invoke(
                IngredientsToSearch(mutableListOf("1", "2", "3", "4")),
                "teste"
            )
        )
    }
}

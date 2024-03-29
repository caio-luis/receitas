package com.caioluis.receitas.usecase

import com.caioluis.receitas.domain.model.IngredientsToSearch
import com.caioluis.receitas.domain.usecase.AddIngredientsOnListUseCase
import org.junit.Assert.assertEquals
import org.junit.Test

class AddIngredientsOnListUseCaseTest {

    private var addIngredientsOnListUseCase: AddIngredientsOnListUseCase =
        AddIngredientsOnListUseCaseImpl()

    @Test
    fun `assert that add one ingredient to empty list is correct`() {
        assertEquals(
            IngredientsToSearch(mutableListOf("teste")),
            addIngredientsOnListUseCase.invoke(
                IngredientsToSearch(),
                "teste"
            )
        )
    }

    @Test
    fun `assert that add ingredient to list with 4 items reach the limit`() {
        assertEquals(
            IngredientsToSearch(mutableListOf("1", "2", "3", "4", "5"), limitReached = true),
            addIngredientsOnListUseCase.invoke(
                IngredientsToSearch(mutableListOf("1", "2", "3", "4")),
                "5"
            )
        )
    }

    @Test
    fun `assert that do not add a ingredient that is already in the list`() {
        assertEquals(
            IngredientsToSearch(mutableListOf("1", "2", "3", "4")),
            addIngredientsOnListUseCase.invoke(
                IngredientsToSearch(mutableListOf("1", "2", "3", "4")),
                "4"
            )
        )
    }

    @Test
    fun `assert that add ingredient to list with 3 items do not reach the limit`() {
        assertEquals(
            IngredientsToSearch(mutableListOf("1", "2", "3", "4")),
            addIngredientsOnListUseCase.invoke(
                IngredientsToSearch(mutableListOf("1", "2", "3")),
                "4"
            )
        )
    }
}

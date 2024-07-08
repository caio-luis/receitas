package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.model.IngredientsToSearch
import com.caioluis.receitas.domain.usecase.AddIngredientsOnListUseCase
import com.caioluis.receitas.domain.usecase.RemoveIngredientUseCase
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

@RunWith(Parameterized::class)
class RecipesReducerTest(private val parameter: Parameter) {

    private lateinit var recipesReducer: RecipesReducerImpl

    private var addIngredientsOnListUseCase: AddIngredientsOnListUseCase = mockk(relaxed = true)

    private var removeIngredientUseCase: RemoveIngredientUseCase = mockk(relaxed = true)

    companion object {
        @JvmStatic
        @Parameterized.Parameters(name = "{0}")
        fun parameters(): List<Parameter> {

            return listOf(
                RecipesReducerParameters.showRecipesCase,
                RecipesReducerParameters.showRecipesWithEmptyResultCase,
                RecipesReducerParameters.addOneIngredientCase,
                RecipesReducerParameters.removeOneIngredientFromListCase,
                RecipesReducerParameters.errorCase,
                RecipesReducerParameters.loadingRecipesCase
            )
        }
    }

    data class Parameter(
        val effect: RecipesEffect,
        val initialState: RecipesState = RecipesState(),
        val finalState: RecipesState,
    ) {
        override fun toString() = "${effect.javaClass.simpleName} should return state correctly"
    }

    @Before
    fun setup() {
        recipesReducer = RecipesReducerImpl(addIngredientsOnListUseCase, removeIngredientUseCase)
        every {
            addIngredientsOnListUseCase.invoke(
                ingredients = IngredientsToSearch(), ingredient = "teste"
            )
        } returns IngredientsToSearch(mutableListOf("teste"))

        every {
            removeIngredientUseCase.invoke(
                ingredients = IngredientsToSearch(), ingredient = "teste"
            )
        } returns IngredientsToSearch()
    }

    @Test
    fun `when effect is `() {
        assertEquals(
            parameter.finalState,
            recipesReducer.invoke(
                state = RecipesState(),
                effect = parameter.effect
            )
        )
    }
}

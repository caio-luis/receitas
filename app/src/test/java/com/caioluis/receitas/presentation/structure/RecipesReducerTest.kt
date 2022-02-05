package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.domain.usecase.AddIngredientsOnListUseCase
import com.caioluis.receitas.domain.usecase.RemoveIngredientUseCase
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(Parameterized::class)
class RecipesReducerTest(private val parameter: Parameter) {

    private lateinit var recipesReducer: RecipesReducerImpl

    @Mock
    private var addIngredientsOnListUseCase: AddIngredientsOnListUseCase =
        mock(AddIngredientsOnListUseCase::class.java)

    @Mock
    private var removeIngredientUseCase: RemoveIngredientUseCase =
        mock(RemoveIngredientUseCase::class.java)

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

        `when`(
            addIngredientsOnListUseCase.invoke(
                ingredients = IngredientsToSearch(), ingredient = "teste"
            )
        ).thenReturn(IngredientsToSearch(mutableListOf("teste")))

        `when`(
            removeIngredientUseCase.invoke(
                ingredients = IngredientsToSearch(), ingredient = "teste"
            )
        ).thenReturn(IngredientsToSearch())
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

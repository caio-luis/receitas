package com.caioluis.receitas.presentation.structure

import com.caioluis.receitas.Fixtures
import com.caioluis.receitas.domain.usecase.AddIngredientsOnListUseCase
import com.caioluis.receitas.domain.usecase.AddIngredientsToListUseCaseImpl
import com.caioluis.receitas.domain.usecase.RemoveIngredientUseCase
import com.caioluis.receitas.presentation.mapper.toViewModel
import com.caioluis.receitas.toDomain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.mockito.Mock
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

            val errorMock = Throwable("test error")
            val recipesMock = Fixtures.getRecipesMockFromJsonResource().map { it.toDomain() }

            return listOf(
                Parameter(
                    effect = RecipesEffect.ShowRecipes(recipes = listOf()),
                    finalState = RecipesState(
                        loading = false,
                        recipes = listOf(),
                        ingredientsToSearch = mutableListOf(),
                        error = null
                    )
                ),
                Parameter(
                    effect = RecipesEffect.AddIngredient(ingredient = "teste"),
                    finalState = RecipesState(
                        loading = false,
                        recipes = listOf(),
                        ingredientsToSearch = mutableListOf("teste"),
                        error = null
                    )
                ),
                Parameter(
                    effect = RecipesEffect.RemoveIngredient(ingredient = "teste"),
                    finalState = RecipesState(
                        loading = false,
                        recipes = listOf(),
                        ingredientsToSearch = mutableListOf(),
                        error = null
                    )
                ),
                Parameter(
                    effect = RecipesEffect.Loading,
                    finalState = RecipesState(
                        loading = true,
                        recipes = listOf(),
                        ingredientsToSearch = mutableListOf(),
                        error = null
                    )
                ),
                Parameter(
                    effect = RecipesEffect.Error(errorMock),
                    finalState = RecipesState(
                        loading = false,
                        recipes = listOf(),
                        ingredientsToSearch = mutableListOf(),
                        error = errorMock
                    )
                ),
                Parameter(
                    effect = RecipesEffect.ShowRecipes(listOf()),
                    initialState = RecipesState(
                        loading = false,
                        recipes = recipesMock.map { it.toViewModel() },
                        ingredientsToSearch = mutableListOf(),
                        error = null
                    ),
                    finalState = RecipesState(
                        loading = false,
                        recipes = listOf(),
                        ingredientsToSearch = mutableListOf(),
                        error = null
                    )
                ),
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
    }

    @Test
    fun `when effect is `() {
        assertEquals(
            recipesReducer.invoke(
                state = RecipesState(),
                effect = parameter.effect
            ),
            parameter.finalState
        )
    }
}

package com.caioluis.receitas.data

import com.caioluis.receitas.data.local.LocalSource
import com.caioluis.receitas.data.remote.RemoteSource
import com.caioluis.receitas.domain.model.DomainRecipe
import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

internal class RecipesDataSourceImplTest {

    private lateinit var recipesDataSource: RecipesDataSource
    private lateinit var localImpl: LocalSource
    private lateinit var remoteImpl: RemoteSource

    @Before
    fun setup() {
        localImpl = mockk(relaxed = true)
        remoteImpl = mockk(relaxed = true)
        recipesDataSource = RecipesDataSourceImpl(localImpl, remoteImpl)
    }

    @Test
    fun `getRecipes should return remote recipes when available`() {
        // Given
        val ingredients = listOf("Ingredient 1", "Ingredient 2")
        val remoteRecipes = listOf(DomainRecipe(), DomainRecipe())

        every { remoteImpl.getRecipes(ingredients) } returns Observable.just(remoteRecipes)
        every { localImpl.getRecipes(ingredients) } returns Observable.empty()

        // When
        val testObserver = recipesDataSource.getRecipes(ingredients).test()

        // Then
        testObserver.assertValue(remoteRecipes)
        testObserver.assertComplete()
    }

    @Test
    fun `getRecipes should return remote recipes when has local recipes`() {
        // Given
        val ingredients = listOf("Ingredient 1", "Ingredient 2")
        val remoteRecipes = listOf(DomainRecipe(), DomainRecipe())
        val localRecipes = listOf(DomainRecipe())

        every { remoteImpl.getRecipes(ingredients) } returns Observable.just(remoteRecipes)
        every { localImpl.getRecipes(ingredients) } returns Observable.just(localRecipes)

        // When
        val testObserver = recipesDataSource.getRecipes(ingredients).test()

        // Then
        testObserver.assertValue(remoteRecipes)
        testObserver.assertComplete()
    }

    @Test
    fun `getRecipes should return local recipes when remote recipes are not available`() {
        // Given
        val ingredients = listOf("Ingredient 1", "Ingredient 2")
        val localRecipes = listOf(DomainRecipe(), DomainRecipe())

        every { remoteImpl.getRecipes(ingredients) } returns Observable.empty()
        every { localImpl.getRecipes(ingredients) } returns Observable.just(localRecipes)

        // When
        val testObserver = recipesDataSource.getRecipes(ingredients).test()

        // Then
        testObserver.assertValue(localRecipes)
        testObserver.assertComplete()
    }

    @Test
    fun `getRecipes should return local recipes when remote recipes are not available and onErrorResumeNext is called`() {
        // Given
        val ingredients = listOf("Ingredient 1", "Ingredient 2")
        val localRecipes = listOf(DomainRecipe(), DomainRecipe())

        every { remoteImpl.getRecipes(ingredients) } returns Observable.error(Exception("Error"))
        every { localImpl.getRecipes(ingredients) } returns Observable.just(localRecipes)

        // When
        val testObserver = recipesDataSource.getRecipes(ingredients).test()

        // Then
        testObserver.assertValue(localRecipes)
        testObserver.assertComplete()
    }

    @Test
    fun `getRecipes should return error when both are errors`() {
        // Given
        val ingredients = listOf("Ingredient 1", "Ingredient 2")

        val error = Exception("Error")

        every { remoteImpl.getRecipes(ingredients) } returns Observable.error(error)
        every { localImpl.getRecipes(ingredients) } returns Observable.error(error)

        // When
        val testObserver = recipesDataSource.getRecipes(ingredients).test()

        // Then
        testObserver.assertError(error)
    }
}
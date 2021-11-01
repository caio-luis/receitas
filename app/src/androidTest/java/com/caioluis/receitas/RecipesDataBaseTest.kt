package com.caioluis.receitas

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.caioluis.receitas.data.local.database.RecipesDao
import com.caioluis.receitas.data.local.database.RecipesDataBase
import com.caioluis.receitas.data.local.mapper.IngredientsSearchSqlQueryMapper
import com.caioluis.receitas.data.model.Recipe
import io.reactivex.Observable
import io.reactivex.Single
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RecipesDataBaseTest {

    private lateinit var db: RecipesDataBase
    private lateinit var dao: RecipesDao
    private lateinit var context: Context
    private lateinit var ingredientsSearchSqlQueryMapper: IngredientsSearchSqlQueryMapper

    @Before
    fun setUp() {
        ingredientsSearchSqlQueryMapper = IngredientsSearchSqlQueryMapper.Impl()
        context = ApplicationProvider.getApplicationContext()
        db = Room.inMemoryDatabaseBuilder(context, RecipesDataBase::class.java).build()
        dao = db.recipesDao()
    }

    @Test
    fun writeAndReadDatabase() {
        val entities = Fixtures.recipes

        val writeObservable = Observable.just(dao.insertRecipes(entities))


        val readObservable = dao.getRecipesByIngredients(
            ingredientsSearchSqlQueryMapper(listOf("ovo", "sal"))
        )

        writeObservable.test().assertComplete().assertNoErrors()

        readObservable
            .test()
            .assertValue { it.isNotEmpty() && entities.subList(0, 2) == it }
            .assertComplete()
            .assertNoErrors()
    }

    @After
    fun closeDatabase() {
        db.close()
    }
}
package com.caioluis.receitas.data.local.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.RawQuery
import androidx.sqlite.db.SupportSQLiteQuery
import com.caioluis.receitas.data.model.Recipe
import io.reactivex.Single

@Dao
interface RecipesDao {
    @RawQuery
    fun getRecipesByIngredients(parameters: SupportSQLiteQuery): Single<List<Recipe>>

    @Insert(entity = Recipe::class, onConflict = REPLACE)
    fun insertRecipes(parameters: List<Recipe>)
}
package com.caioluis.receitas.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.caioluis.receitas.data.model.Recipe

@Database(
    entities = [Recipe::class],
    version = 1,
    exportSchema = false
)

@TypeConverters(SectionTypeConverter::class, IngredientsTypeConverter::class)
abstract class RecipesDataBase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

    companion object {

        const val RECIPES_TABLE_NAME = "receitas"

        private var INSTANCE: RecipesDataBase? = null

        fun getInstance(context: Context): RecipesDataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RecipesDataBase::class.java, "recipes.db"
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}
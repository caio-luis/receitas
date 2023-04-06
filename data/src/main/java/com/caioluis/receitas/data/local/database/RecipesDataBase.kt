package com.caioluis.receitas.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.caioluis.receitas.data.local.database.DataBaseConstants.DATABASE_NAME
import com.caioluis.receitas.data.local.database.DataBaseConstants.DATABASE_VERSION
import com.caioluis.receitas.data.local.model.Recipe

@Database(
    entities = [Recipe::class],
    version = DATABASE_VERSION,
    exportSchema = false
)

@TypeConverters(SectionTypeConverter::class, IngredientsTypeConverter::class)
abstract class RecipesDataBase : RoomDatabase() {

    abstract fun recipesDao(): RecipesDao

    companion object {

        private var INSTANCE: RecipesDataBase? = null

        fun getInstance(context: Context): RecipesDataBase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                RecipesDataBase::class.java, DATABASE_NAME
            )
                .fallbackToDestructiveMigration()
                .build()
    }
}
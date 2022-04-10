package com.caioluis.receitas.data.local.mapper

import androidx.sqlite.db.SupportSQLiteProgram
import androidx.sqlite.db.SupportSQLiteQuery
import com.caioluis.receitas.data.local.database.RecipesDataBase.Companion.RECIPES_TABLE_NAME
import java.util.Locale

interface IngredientsSearchSqlQueryMapper {
    operator fun invoke(ingredients: List<String>): SupportSQLiteQuery

    class Impl : IngredientsSearchSqlQueryMapper {

        override fun invoke(ingredients: List<String>): SupportSQLiteQuery {

            val stringBuilder: StringBuilder = StringBuilder()

            val sqlQuery = getSupportSqlQuery(ingredients.size, stringBuilder)

            if (ingredients.isEmpty())
                return sqlQuery

            stringBuilder.append("SELECT * FROM $RECIPES_TABLE_NAME WHERE ingredients ")

            ingredients.forEachIndexed { index, string ->
                val trimmedString = string.trim().lowercase(Locale.getDefault())
                if (index == ingredients.lastIndex)
                    stringBuilder.append("LIKE '%$trimmedString%'")
                else
                    stringBuilder.append("LIKE '%$trimmedString%' OR ingredients ")
            }

            return sqlQuery
        }

        private fun getSupportSqlQuery(
            parametersSize: Int,
            query: StringBuilder
        ): SupportSQLiteQuery {
            return object : SupportSQLiteQuery {
                override fun getSql(): String {
                    return query.toString()
                }

                override fun getArgCount(): Int {
                    return parametersSize
                }

                override fun bindTo(statement: SupportSQLiteProgram?) = Unit
            }
        }
    }
}
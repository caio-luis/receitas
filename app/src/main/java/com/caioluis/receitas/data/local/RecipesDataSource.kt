package com.caioluis.receitas.data.local

import androidx.sqlite.db.SupportSQLiteProgram
import androidx.sqlite.db.SupportSQLiteQuery
import com.caioluis.receitas.data.local.database.RecipesDao
import com.caioluis.receitas.data.local.mapper.RecipesLocalMapper
import com.caioluis.receitas.domain.model.DomainRecipe
import io.reactivex.Observable

interface RecipesDataSource {
    fun getRecipes(ingredients: List<String>): Observable<List<DomainRecipe>>

    class LocalImpl(
        private val dao: RecipesDao,
        private val mapper: RecipesLocalMapper,
        private val queryMapperIngredientsSearch: IngredientsSearchSqlQueryMapper
    ) : RecipesDataSource {
        override fun getRecipes(ingredients: List<String>): Observable<List<DomainRecipe>> {
            return dao.getRecipesByIngredients(queryMapperIngredientsSearch(ingredients))
                .map { mapper.mapRecipes(it) }
                .toObservable()
        }
    }

    interface IngredientsSearchSqlQueryMapper {
        operator fun invoke(ingredients: List<String>): SupportSQLiteQuery

        class Impl() : IngredientsSearchSqlQueryMapper {

            override fun invoke(ingredients: List<String>): SupportSQLiteQuery {

                val stringBuilder: StringBuilder = StringBuilder()

                val sqlQuery = object : SupportSQLiteQuery {
                    override fun getSql(): String {
                        return stringBuilder.toString()
                    }

                    override fun bindTo(statement: SupportSQLiteProgram?) {

                    }

                    override fun getArgCount(): Int {
                        return ingredients.size
                    }

                }

                if (ingredients.isEmpty())
                    return sqlQuery

                stringBuilder.append("SELECT * FROM receitas WHERE sections ")

                ingredients.forEachIndexed { index, string ->
                    val trimmedString = string.trim()
                    if (index == ingredients.lastIndex)
                        stringBuilder.append("LIKE '%$trimmedString%'")
                    else
                        stringBuilder.append("LIKE '%$trimmedString%' OR sections ")
                }

                return sqlQuery
            }
        }
    }
}
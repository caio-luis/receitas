package com.caioluis.receitas.data.local

import com.caioluis.receitas.data.local.database.RecipesDao
import com.caioluis.receitas.data.local.mapper.IngredientsSearchSqlQueryMapper
import com.caioluis.receitas.data.local.mapper.RecipesLocalMapper
import com.caioluis.receitas.domain.model.DomainRecipe
import io.reactivex.Observable

class LocalImpl(
    private val dao: RecipesDao,
    private val mapper: RecipesLocalMapper,
    private val queryMapperIngredientsSearch: IngredientsSearchSqlQueryMapper
) : LocalSource {
    override fun getRecipes(ingredients: List<String>): Observable<List<DomainRecipe>> {
        return dao.getRecipesByIngredients(queryMapperIngredientsSearch(ingredients))
            .map { mapper.mapRecipes(it) }
            .toObservable()
    }
}

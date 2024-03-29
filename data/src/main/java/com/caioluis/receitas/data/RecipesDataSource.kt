package com.caioluis.receitas.data

import com.caioluis.receitas.domain.model.DomainRecipe
import io.reactivex.Observable

interface RecipesDataSource {
    fun getRecipes(ingredients: List<String>): Observable<List<DomainRecipe>>
}

package com.caioluis.receitas.data

import com.caioluis.receitas.data.local.LocalSource
import com.caioluis.receitas.data.remote.RemoteSource
import com.caioluis.receitas.domain.model.DomainRecipe
import io.reactivex.Observable

class RecipesDataSourceImpl(
    private val localImpl: LocalSource,
    private val remoteImpl: RemoteSource,
) : RecipesDataSource {
    override fun getRecipes(ingredients: List<String>): Observable<List<DomainRecipe>> {

        return remoteImpl.getRecipes(ingredients)
            .onErrorResumeNext(localImpl.getRecipes(ingredients))
            .switchIfEmpty(localImpl.getRecipes(ingredients))
    }
}
